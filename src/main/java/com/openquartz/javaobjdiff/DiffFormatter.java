package com.openquartz.javaobjdiff;

/**
 * DiffFormat
 *
 * @author svnee
 */
public interface DiffFormatter {

    /**
     * format
     *
     * @param value value
     * @return format result
     */
    Object format(Object value, String pattern);
}
