package com.openquartz.javaobjdiff.annotation;

import com.openquartz.javaobjdiff.DiffFormatter;
import com.openquartz.javaobjdiff.formatter.DefaultDiffFormatter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author svnee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DiffFormat {

    /**
     * time format
     */
    Class<? extends DiffFormatter> using() default DefaultDiffFormatter.class;

    /**
     * formatter pattern
     *
     * @return pattern
     */
    String pattern() default "";
}
