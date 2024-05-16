package com.openquartz.javaobjdiff.formatter.datamask;

import com.openquartz.javaobjdiff.annotation.DiffFormat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MobileDataMask
 * @author svnee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@DiffFormat(using = MobileDataMaskFormatter.class)
public @interface MobileDataMask {

}
