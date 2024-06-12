package com.openquartz.javaobjdiff;

import static com.openquartz.javaobjdiff.CommonConstants.POINT_SPLITTER;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Simple To String Style
 *
 * @author svnee
 */
public class SimplePrefixToStringStyle extends ToStringStyle {

    private final String prefix;
    private boolean useFieldNames = true;
    private String fieldNameValueSeparator = "=";

    public SimplePrefixToStringStyle(String prefix) {
        this.prefix = prefix;
        setUseShortClassName(true);
        setUseIdentityHashCode(false);
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        buffer.append(value);
    }

    /**
     * <p>Sets whether to use the field names passed in.</p>
     *
     * @param useFieldNames the new useFieldNames flag
     */
    @Override
    protected void setUseFieldNames(final boolean useFieldNames) {
        this.useFieldNames = useFieldNames;
        super.setUseFieldNames(useFieldNames);
    }

    /**
     * <p>Sets whether to use the field names passed
     *
     * @param fieldNameValueSeparator the new field name value separator text
     */
    @Override
    protected void setFieldNameValueSeparator(String fieldNameValueSeparator) {
        if (fieldNameValueSeparator == null) {
            fieldNameValueSeparator = StringUtils.EMPTY;
        }
        this.fieldNameValueSeparator = fieldNameValueSeparator;
        super.setFieldNameValueSeparator(fieldNameValueSeparator);
    }

    @Override
    protected void appendFieldStart(StringBuffer buffer, String fieldName) {

        if (this.useFieldNames && fieldName != null) {
            String newFiledName = Stream.of(prefix, fieldName)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(POINT_SPLITTER));
            buffer.append(newFiledName);
            buffer.append(fieldNameValueSeparator);
        }
    }
}
