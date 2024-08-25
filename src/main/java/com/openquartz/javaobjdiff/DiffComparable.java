package com.openquartz.javaobjdiff;

/**
 * Diff Comparable
 *
 * @author svnee
 */
public interface DiffComparable {

    /**
     * 是否不同
     *
     * @param source source
     * @param target target
     * @return 是否不同,false: 相同, true: 不同
     */
    boolean diff(Object source, Object target);
}
