package com.openquartz.javaobjdiff.annotation;

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
    String pattern() default "yyyy-MM-dd hh:mm:ss";
}
