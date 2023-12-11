package com.openquartz.javaobjdiff;

import static com.openquartz.javaobjdiff.util.FieldUtils.readField;
import static org.apache.commons.lang3.ClassUtils.INNER_CLASS_SEPARATOR_CHAR;

import com.openquartz.javaobjdiff.annotation.DiffBean;
import com.openquartz.javaobjdiff.annotation.DiffIgnore;
import com.openquartz.javaobjdiff.util.ClassUtils;
import com.openquartz.javaobjdiff.util.FieldUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CacheReflectionDiffBuilder
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

        if (Collection.class.isAssignableFrom(clazz)) {
            diffBuilder.append("element", StringUtils.EMPTY, left, right);
            return;
        }

        if (Map.class.isAssignableFrom(clazz)) {
            diffBuilder.append("map", StringUtils.EMPTY, left, right);
            return;
        }

        appendAllFields(StringUtils.EMPTY, clazz, left, right);
    }

    private void appendAllFields(String prefix, Class<?> clazz, Object leftValue, Object rightValue) {

        if (leftValue == null && rightValue == null) {
            return;
        }

        for (final Field field : getAllFields(clazz)) {

            if (accept(field)) {
                try {

                    Object lhs = readField(field, leftValue, true);
                    Object rhs = readField(field, rightValue, true);

                    diffBuilder.append(prefix, field, lhs, rhs);

                    // append diff bean
                    DiffBean diffBean = field.getDeclaredAnnotation(DiffBean.class);
                    if (diffBean != null && !ClassUtils.isJDKClass(field.getType())) {

                        String specPrefix = Stream.of(prefix, field.getName())
                            .filter(StringUtils::isNotBlank)
                            .collect(Collectors.joining(CommonConstants.POINT_SPLITTER));

                        appendAllFields(specPrefix, field.getType(), lhs, rhs);
                    }
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

            List<Field> fieldList = FIELD_CACHE.get(clazz);
            if (fieldList != null) {
                return fieldList;
            }

            List<Field> effectiveFields = getEffectiveFields(clazz);
            FIELD_CACHE.putIfAbsent(clazz, effectiveFields);
            return effectiveFields;
        } catch (Exception exception) {
            log.info("[CacheReflectionDiffBuilder#getAllFields] class:{} execute error!", clazz);
            return ExceptionUtils.rethrow(exception);
        }
    }

}
