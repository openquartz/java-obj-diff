package com.openquartz.javaobjdiff.formatter;

import com.openquartz.javaobjdiff.DiffFormatter;

/**
 * DefaultDiffFormatter
 *
 * @author svnee
 */
public class DefaultDiffFormatter implements DiffFormatter {

    @Override
    public Object format(Object value, String pattern) {
        return value;
    }
}
