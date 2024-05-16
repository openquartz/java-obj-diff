package com.openquartz.javaobjdiff.formatter.datamask;

import com.openquartz.javaobjdiff.annotation.DiffFormat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IdCardDataMask
 * @author svnee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@DiffFormat(using = IdCardDataMaskFormatter.class)
public @interface IdCardDataMask {

}
