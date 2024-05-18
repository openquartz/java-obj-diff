package com.openquartz.javaobjdiff.formatter.datamask;

import com.openquartz.javaobjdiff.DiffFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * EmailDataMask
 * @author svnee
 */
public class EmailDataMaskFormatter implements DiffFormatter {

    @Override
    public Object format(Object value, String pattern) {

        if (value == null) {
            return null;
        }

        if (!(value instanceof String)) {
            throw new IllegalArgumentException("not support value type!type must be String");
        }

        String email = (String) value;

        return desensitizeEmail(email);
    }

    public static String desensitizeEmail(String email) {

        if (StringUtils.isBlank(email) || !email.contains("@")) {
            return email;
        }

        int atIndex = email.indexOf("@");
        String prefix = email.substring(0, atIndex);
        int lengthToReplace = prefix.length() - 1;

        String replacement = Stream.generate(() -> "*")
            .limit(lengthToReplace)
            .collect(Collectors.joining());

        return replacement + prefix.charAt(0) + "@" + email.substring(atIndex);
    }

}
