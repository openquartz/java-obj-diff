package com.openquartz.javaobjdiff.annotation;

import com.openquartz.javaobjdiff.DiffCollectionSortTypeEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 集合比较不同
 *
 * @author svnee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DiffCollection {

    /**
     * 排序名
     */
    String sortFiledName() default "";

    /**
     * 排序类型
     */
    DiffCollectionSortTypeEnum sortType() default DiffCollectionSortTypeEnum.ASC;
}
