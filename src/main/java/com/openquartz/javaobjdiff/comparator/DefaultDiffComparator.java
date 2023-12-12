package com.openquartz.javaobjdiff.comparator;

import com.openquartz.javaobjdiff.DiffComparable;
import java.util.Objects;

/**
 * 默认比较器
 *
 * @author svnee
 */
public class DefaultDiffComparator implements DiffComparable {

    @Override
    public boolean diff(Object source, Object target) {

        if (source == target) {
            return false;
        }

        return !Objects.equals(source, target);
    }
}
