package com.openquartz.javaobjdiff;

import org.apache.commons.lang3.builder.ToStringStyle;
import com.openquartz.javaobjdiff.util.IteratorUtils;

/**
 * @author svnee
 */
public class DiffUtils {

    private static final String EMPTY = "";

    private DiffUtils() {
    }

    public static <T> String diff(T source, T target, ToStringStyle toStringStyle, String... excludeField) {
        return buildDiff(source, target, toStringStyle, excludeField).toString();
    }

    public static <T> String diff(T source, T target, String prefix, String... excludeField) {
        ToStringStyle toStringStyle = new SimplePrefixToStringStyle(prefix);
        return buildDiff(source, target, toStringStyle, excludeField).toString();
    }

    public static <T> String diff(T source, T target) {
        return diff(source, target, EMPTY, new String[]{});
    }

    public static <T> String diff(T source, T target, String... excludeField) {
        return diff(source, target, EMPTY, excludeField);
    }

    /**
     * Compare differences and return {@link DiffResult} for more flexible operation and judgment.
     *
     * @param source       Source object
     * @param target       Target object
     * @param prefix       Specified prefix, default is empty string
     * @param excludeField Fields to exclude from comparison
     * @param <T>          Type of objects to compare
     * @return {@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target, String prefix, String... excludeField) {
        ToStringStyle toStringStyle = new SimplePrefixToStringStyle(prefix);
        return buildDiff(source, target, toStringStyle, excludeField);
    }

    /**
     * Compare differences and return {@link DiffResult} for more flexible operation and judgment.
     *
     * @param source Source object
     * @param target Target object
     * @param <T>    Type of objects to compare
     * @return {@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target) {
        return diffObj(source, target, EMPTY, new String[]{});
    }

    /**
     * Compare differences and return {@link DiffResult} for more flexible operation and judgment.
     *
     * @param source       Source object
     * @param target       Target object
     * @param excludeField Fields to exclude from comparison
     * @param <T>          Type of objects to compare
     * @return {@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target, String... excludeField) {
        return diffObj(source, target, EMPTY, excludeField);
    }

    private static <T> DiffResult<T> buildDiff(T source, T target, ToStringStyle toStringStyle, String... excludeField) {
        CacheReflectionDiffBuilder<T> diffBuilder = new CacheReflectionDiffBuilder<>(source, target, toStringStyle);
        diffBuilder.setExcludeFieldSet(IteratorUtils.toSet(excludeField));
        return diffBuilder.build();
    }
}
