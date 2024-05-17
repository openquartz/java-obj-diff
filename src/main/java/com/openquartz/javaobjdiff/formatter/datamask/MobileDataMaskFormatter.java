package com.openquartz.javaobjdiff.formatter.datamask;

import com.openquartz.javaobjdiff.DiffFormatter;
import java.util.regex.Pattern;

/**
 * 手机号脱敏
 * @author svnee
 */
public class MobileDataMaskFormatter implements DiffFormatter {

    // 正则表达式匹配手机号码
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    // 正则表达式匹配座机号码，这里简化处理，实际情况可能更复杂
    private static final Pattern LANDLINE_PATTERN = Pattern.compile("\\d{3,4}-\\d{7,8}");

    @Override
    public Object format(Object value, String pattern) {

        if (value == null) {
            return null;
        }

        if (!(value instanceof String)) {
            throw new IllegalArgumentException("not support value type!type must be String");
        }

        String mobile = (String) value;

        return desensitizePhoneNumber(mobile);
    }

    public static String desensitizePhoneNumber(String phoneNumber) {
        if (MOBILE_PATTERN.matcher(phoneNumber).matches()) {
            // 手机号码脱敏处理
            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
        } else if (LANDLINE_PATTERN.matcher(phoneNumber).matches()) {
            // 座机号码脱敏处理，这里假设区号为3-4位，本地号码为7-8位
            String[] parts = phoneNumber.split("-");
            String areaCode = parts[0];
            String localNumber = parts[1];
            return areaCode + "-****" + localNumber.substring(localNumber.length() - 4);
        } else {
            return phoneNumber;
        }
    }
}
