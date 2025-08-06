package com.openquartz.javaobjdiff;

import com.openquartz.javaobjdiff.util.IteratorUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author svnee
 */
public class DiffUtils {

    private static final String EMPTY = "";

    private DiffUtils() {
    }

    public static <T> String diff(T source, T target, ToStringStyle toStringStyle, String... excludeFiled) {
        CacheReflectionDiffBuilder<T> diffBuilder = new CacheReflectionDiffBuilder<>(source, target, toStringStyle);
        diffBuilder.setExcludeFieldSet(IteratorUtils.toSet(excludeFiled));
        return diffBuilder.build().toString();
    }

    public static <T> String diff(T source, T target, String prefix, String... excludeFiled) {
        CacheReflectionDiffBuilder<T> diffBuilder =
                new CacheReflectionDiffBuilder<>(source, target, new SimplePrefixToStringStyle(prefix));
        diffBuilder.setExcludeFieldSet(IteratorUtils.toSet(excludeFiled));
        return diffBuilder.build().toString();
    }

    public static <T> String diff(T source, T target) {
        return diff(source, target, EMPTY, new String[]{});
    }

    public static <T> String diff(T source, T target, String... excludeField) {
        return diff(source, target, EMPTY, excludeField);
    }

    public static <T> DiffResult<T> diffResult(T source, T target) {
        CacheReflectionDiffBuilder<T> diffBuilder =
                new CacheReflectionDiffBuilder<>(source, target, new SimplePrefixToStringStyle(EMPTY));
        return diffBuilder.build();
    }

    public static <T> DiffResult<T> diffResult(T source, T target, String... excludeField) {
        CacheReflectionDiffBuilder<T> diffBuilder =
                new CacheReflectionDiffBuilder<>(source, target, new SimplePrefixToStringStyle(EMPTY));
        diffBuilder.setExcludeFieldSet(IteratorUtils.toSet(excludeField));
        return diffBuilder.build();
    }
}
