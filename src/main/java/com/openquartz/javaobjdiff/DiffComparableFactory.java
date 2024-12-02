package com.openquartz.javaobjdiff;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * DiffComparableFactory
 * @author svnee
 */
public class DiffComparableFactory {

    private DiffComparableFactory() {
    }

    private static final Map<Class<? extends DiffComparable>, DiffComparable> diffComparableInstanceMap =
        new ConcurrentHashMap<>();

    /**
     * get diffComparable
     * @param diffComparableClass diffComparable class
     * @return diffComparable
     */
    public static DiffComparable get(Class<? extends DiffComparable> diffComparableClass) {

        DiffComparable diffComparable = diffComparableInstanceMap.get(diffComparableClass);
        if (diffComparable != null) {
            return diffComparable;
        }

        try {
            // 默认使用无参构造做实例化
            DiffComparable instance = diffComparableClass.getDeclaredConstructor().newInstance();
            DiffComparable actualInstance = diffComparableInstanceMap.putIfAbsent(diffComparableClass, instance);
            return actualInstance == null ? instance : actualInstance;
        } catch (Exception ex) {
            return ExceptionUtils.rethrow(ex);
        }
    }

    /**
     * register diffComparable
     * @param diffComparableClass diffComparable class
     * @param diffComparable diffComparable
     */
    public static void register(Class<? extends DiffComparable> diffComparableClass, DiffComparable diffComparable) {
        diffComparableInstanceMap.computeIfAbsent(diffComparableClass, key -> diffComparable);
    }

}
