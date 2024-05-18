package com.openquartz.javaobjdiff.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author svnee
 */
public class IteratorUtils {

    private IteratorUtils() {
    }


    /**
     * has next
     *
     * @param iterator iterator
     * @return has next ?
     */
    public static boolean hasNext(Iterator<?> iterator) {
        if (Objects.nonNull(iterator)) {
            return iterator.hasNext();
        }
        return false;
    }

    /**
     * next value in iterator
     *
     * @param iterator iterator
     * @param <T> next value type
     * @return next value`
     */
    public static <T> T next(Iterator<T> iterator) {
        if (Objects.nonNull(iterator) && iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @SafeVarargs
    public static <T> Set<T> toSet(T... args) {

        if (args == null || args.length == 0) {
            return new HashSet<>();
        }

        Set<T> set = new HashSet<>(args.length);
        set.addAll(Arrays.asList(args));
        return set;
    }

    public static <T> List<T> toList(T... args) {
        if (args == null || args.length == 0) {
            return new ArrayList<>();
        }

        List<T> list = new ArrayList<>(args.length);
        list.addAll(Arrays.asList(args));
        return list;
    }
}
