package com.openquartz.javaobjdiff.util;

import java.util.Iterator;
import java.util.Objects;

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
}
