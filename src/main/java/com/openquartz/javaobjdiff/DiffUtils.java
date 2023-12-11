package com.openquartz.javaobjdiff;

import java.util.Set;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiffUtils {

    private DiffUtils() {
    }

    public static <T> String diff(T source, T target, ToStringStyle toStringStyle, String... excludeFiled) {
        CacheReflectionDiffBuilder<T> diffBuilder = new CacheReflectionDiffBuilder<>(source, target, toStringStyle);
        diffBuilder.setExcludeFieldSet(Set.of(excludeFiled));
        return diffBuilder.build().toString();
    }

}
