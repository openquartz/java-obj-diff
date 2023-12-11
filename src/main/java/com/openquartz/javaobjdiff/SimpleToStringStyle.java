package com.openquartz.javaobjdiff;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Simple To String Style
 *
 * @author svnee
 */
public class SimpleToStringStyle extends ToStringStyle {

    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {

        if (value instanceof Date) {
            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }

        buffer.append(value);
    }
}
