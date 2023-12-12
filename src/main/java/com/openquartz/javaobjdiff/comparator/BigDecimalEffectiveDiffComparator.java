package com.openquartz.javaobjdiff.comparator;

import com.openquartz.javaobjdiff.DiffComparable;
import java.math.BigDecimal;

/**
 * 忽略为0 的精度进行比较有效的位数
 *
 * @author svnee
 */
public class BigDecimalEffectiveDiffComparator implements DiffComparable {

    @Override
    public boolean diff(Object source, Object target) {

        if (source == target) {
            return false;
        }

        if (source == null) {
            return true;
        }

        if (target == null) {
            return true;
        }

        if (!(source instanceof BigDecimal) || !(target instanceof BigDecimal)) {
            throw new IllegalArgumentException("source and target must be BigDecimal instances");
        }

        BigDecimal stripTrailingZeroSource = ((BigDecimal) source).stripTrailingZeros();
        BigDecimal stripTrailingZeroTarget = ((BigDecimal) target).stripTrailingZeros();

        return stripTrailingZeroSource.compareTo(stripTrailingZeroTarget) == 0;
    }
}
