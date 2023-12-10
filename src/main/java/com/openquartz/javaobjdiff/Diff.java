package com.openquartz.javaobjdiff;

import java.lang.reflect.Type;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

public abstract class Diff<T> extends Pair<T, T> {

    private static final long serialVersionUID = 1L;

    private final Type type;
    private final String fieldName;

    /**
     * <p>
     * Constructs a new {@code Diff} for the given field name.
     * </p>
     *
     * @param fieldName
     *            the name of the field
     */
    protected Diff(final String fieldName) {
        this.type = ObjectUtils.defaultIfNull(
                TypeUtils.getTypeArguments(getClass(), Diff.class)
                    .get(Diff.class.getTypeParameters()[0]), Object.class);
        this.fieldName = fieldName;
    }

    /**
     * <p>
     * Returns the type of the field.
     * </p>
     *
     * @return the field type
     */
    public final Type getType() {
        return type;
    }

    /**
     * <p>
     * Returns the name of the field.
     * </p>
     *
     * @return the field name
     */
    public final String getFieldName() {
        return fieldName;
    }

    /**
     * <p>
     * Returns a {@code String} representation of the {@code Diff}, with the
     * following format:</p>
     *
     * <pre>
     * [fieldname: left-value, right-value]
     * </pre>
     *
     *
     * @return the string representation
     */
    @Override
    public final String toString() {
        return String.format("[%s: %s, %s]", fieldName, getLeft(), getRight());
    }

    /**
     * <p>
     * Throws {@code UnsupportedOperationException}.
     * </p>
     *
     * @param value
     *            ignored
     * @return nothing
     */
    @Override
    public final T setValue(final T value) {
        throw new UnsupportedOperationException("Cannot alter Diff object.");
    }
}
