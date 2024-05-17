package com.openquartz.javaobjdiff;

import java.util.Set;
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
        diffBuilder.setExcludeFieldSet(Set.of(excludeFiled));
        return diffBuilder.build().toString();
    }

    public static <T> String diff(T source, T target, String prefix, String... excludeFiled) {
        CacheReflectionDiffBuilder<T> diffBuilder =
            new CacheReflectionDiffBuilder<>(source, target, new SimplePrefixToStringStyle(prefix));
        diffBuilder.setExcludeFieldSet(Set.of(excludeFiled));
        return diffBuilder.build().toString();
    }

    public static <T> String diff(T source, T target) {
        return diff(source, target, EMPTY, new String[]{});
    }

    public static <T> String diff(T source, T target, String... excludeField) {
        return diff(source, target, EMPTY, excludeField);
    }
}
