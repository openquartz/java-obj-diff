package com.openquartz.javaobjdiff;

import static com.openquartz.javaobjdiff.FieldUtils.readField;
import static org.apache.commons.lang3.ClassUtils.INNER_CLASS_SEPARATOR_CHAR;

import com.openquartz.javaobjdiff.annotation.DiffIgnore;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReflectionDiffBuilder
 *
 * @author svnee
 */
public class CacheReflectionDiffBuilder<T> implements Builder<DiffResult<T>> {

    private final T left;
    private final T right;
    private final DiffBuilder<T> diffBuilder;

    /**
     * 排除字段
     */
    private Set<String> excludeFieldSet;

    private static final Logger log = LoggerFactory.getLogger(CacheReflectionDiffBuilder.class);

    private static final Map<Class<?>, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>();

    public CacheReflectionDiffBuilder(final T lhs, final T rhs, final ToStringStyle style) {
        this.left = lhs;
        this.right = rhs;
        this.diffBuilder = new DiffBuilder<>(lhs, rhs, style);
    }

    public CacheReflectionDiffBuilder<T> setExcludeFieldSet(Set<String> excludeFieldSet) {
        this.excludeFieldSet = excludeFieldSet;
        return this;
    }

    public DiffResult<T> build() {
        if (left.equals(right)) {
            return diffBuilder.build();
        }

        appendFields(left.getClass());
        return diffBuilder.build();
    }

    private void appendFields(final Class<?> clazz) {
        for (final Field field : getAllFields(clazz)) {
            if (accept(field)) {
                try {

                    Object lhs = readField(field, left, true);
                    Object rhs = readField(field, right, true);

                    diffBuilder.append(field, lhs, rhs);
                } catch (final IllegalAccessException ex) {
                    //this can't happen. Would get a Security exception instead
                    //throw a runtime exception in case the impossible happens.
                    throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
                }
            }
        }
    }

    private boolean accept(final Field field) {
        // 处理排除字段
        String fieldIdentifier = field.getName();
        return excludeFieldSet == null || !excludeFieldSet.contains(fieldIdentifier);
    }

    private static List<Field> getEffectiveFields(Class<?> clazz) {

        List<Field> fieldList = new ArrayList<>();

        for (Field field : FieldUtils.getAllFields(clazz)) {

            boolean isIllegalDeclaredField = field.getName().indexOf(INNER_CLASS_SEPARATOR_CHAR) != -1
                || Modifier.isTransient(field.getModifiers())
                || Modifier.isStatic(field.getModifiers());

            if (isIllegalDeclaredField) {
                continue;
            }
            if (field.getDeclaredAnnotation(DiffIgnore.class) == null) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        try {
            return FIELD_CACHE.putIfAbsent(clazz, getEffectiveFields(clazz));
        } catch (Exception exception) {
            log.info("[CacheFiledUtil#getAllFields] class:{} execute error!", clazz);
            return ExceptionUtils.rethrow(exception);
        }
    }

}
