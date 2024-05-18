package com.openquartz.javaobjdiff.util;

import static java.util.Objects.requireNonNull;

public class Objects {

    public static <T> T requireNonNullElse(T obj, T defaultObj) {
        return (obj != null) ? obj : requireNonNull(defaultObj, "defaultObj");
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

}
