package com.openquartz.javaobjdiff.annotation;

import com.openquartz.javaobjdiff.DiffComparable;
import com.openquartz.javaobjdiff.comparator.DefaultDiffComparator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义比较
 *
 * @author svnee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DiffCompare {

    /**
     * 自定义比较器
     */
    Class<? extends DiffComparable> using() default DefaultDiffComparator.class;

}
