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


    /**
     * 对比差异，并返回{@link DiffResult}，可以更灵活的操作判断
     *
     * @param source       源对象
     * @param target       目标对象
     * @param prefix       指定前缀，默认空字符串
     * @param excludeFiled 需要移除不对比的字段
     * @param <T>
     * @return 返回{@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target, String prefix, String... excludeFiled) {
        CacheReflectionDiffBuilder<T> diffBuilder =
            new CacheReflectionDiffBuilder<>(source, target, new SimplePrefixToStringStyle(prefix));
        diffBuilder.setExcludeFieldSet(IteratorUtils.toSet(excludeFiled));
        return diffBuilder.build();
    }


    /**
     * 对比差异，并返回{@link DiffResult}，可以更灵活的操作判断
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>
     * @return 返回{@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target) {
        return diffObj(source, target, EMPTY, new String[]{});
    }

    /**
     * 对比差异，并返回{@link DiffResult}，可以更灵活的操作判断
     *
     * @param source       源对象
     * @param target       目标对象
     * @param excludeField 需要移除不对比的字段
     * @param <T>
     * @return 返回{@link DiffResult}
     */
    public static <T> DiffResult<T> diffObj(T source, T target, String... excludeField) {
        return diffObj(source, target, EMPTY, excludeField);
    }
}
