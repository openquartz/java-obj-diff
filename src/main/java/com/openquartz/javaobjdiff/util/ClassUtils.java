package com.openquartz.javaobjdiff.util;

public class ClassUtils {

    public static boolean isJDKClass(Class<?> clazz) {
        String className = clazz.getName();
        return className.startsWith("java.lang") ||
            className.startsWith("java.util") ||
            className.startsWith("java.io") ||
            className.startsWith("java.net") ||
            className.startsWith("java.sql") ||
            className.startsWith("java.text") ||
            className.startsWith("java.time") ||
            className.startsWith("java.util.concurrent") ||
            className.startsWith("java.util.regex") ||
            className.startsWith("java.lang.reflect");
    }

}
