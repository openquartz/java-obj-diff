package com.openquartz.javaobjdiff.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import org.apache.commons.lang3.reflect.TypeUtils;

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
            className.startsWith("java.lang.reflect") ||
            className.startsWith("java.math");
    }

    /**
     * Return the type of the content of the given collection type.
     *
     * @param type The collection type.
     * @return Collection type.
     */
    public static Class<?> getCollectionType(Type type) {
        if (TypeUtils.isAssignable(type, Collection.class)) {
            if (type instanceof ParameterizedType) {
                Type genericType = ((ParameterizedType) type).getActualTypeArguments()[0];

                if (genericType instanceof Class) {
                    return (Class<?>) genericType;
                }
            } else {
                throw new IllegalArgumentException("Cannot infer index type for non-parameterized type: " + type);
            }
        } else if (TypeUtils.isArrayType(type)) {
            return (Class<?>) TypeUtils.getArrayComponentType(type);
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }



}
