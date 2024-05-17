package com.openquartz.javaobjdiff.formatter.datamask;

import com.openquartz.javaobjdiff.DiffFormatter;

/**
 * IdCardDataMaskFormatter
 * @author svnee
 */
public class IdCardDataMaskFormatter implements DiffFormatter {

    @Override
    public Object format(Object value, String pattern) {

        if (value == null) {
            return null;
        }

        if (!(value instanceof String)) {
            throw new IllegalArgumentException("not support value type!type must be String");
        }

        String idCard = (String) value;

        return maskIdCard(idCard);
    }


    /**
     * 身份证号码脱敏处理
     * @param idCard 原始身份证号码字符串
     * @return 脱敏后的身份证号码字符串
     */
    public static String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return idCard;
        }

        // 保留前6位和后4位，中间用星号替代

        return idCard.substring(0, 6) + "********" + idCard.substring(14);
    }

}
