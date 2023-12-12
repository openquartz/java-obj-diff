package com.openquartz.javaobjdiff.formatter;

import com.openquartz.javaobjdiff.DiffFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间格式formatter
 *
 * @author svnee
 */
public class DateTimeDiffFormatter implements DiffFormatter {

    private static final String DEFAULT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Object format(Object value, String pattern) {

        if (StringUtils.isBlank(pattern)) {
            pattern = DEFAULT_FORMATTER_PATTERN;
        }

        if (Objects.isNull(value)) {
            return null;
        }

        if (value instanceof Date) {
            return DateFormatUtils.format((Date) value, pattern);
        }

        if (value instanceof TemporalAccessor) {
            return DateTimeFormatter.ofPattern(pattern).format((LocalDateTime) value);
        }

        throw new IllegalArgumentException("Unsupported type: " + value.getClass());
    }
}
