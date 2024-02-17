package com.openquartz.javaobjdiff.test.bean;

import com.openquartz.javaobjdiff.DiffComparable;

public class SelfEnumComparator implements DiffComparable {

    @Override
    public boolean diff(Object source, Object target) {
        if (source instanceof Sex && target instanceof Sex) {
            return ((Sex) source).equals((Sex) target);
        }
        return false;
    }
}
