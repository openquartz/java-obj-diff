package com.openquartz.javaobjdiff;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiffResult<T> implements Iterable<Diff<?>> {

    public static final String OBJECTS_SAME_STRING = "";

    private static final String DIFFERS_STRING = "differs from";

    private final List<Diff<?>> diffList;
    private final T lhs;
    private final T rhs;
    private final ToStringStyle style;

    DiffResult(final T lhs, final T rhs, final List<Diff<?>> diffList,
            final ToStringStyle style) {
        Validate.notNull(lhs, "lhs");
        Validate.notNull(rhs, "rhs");
        Validate.notNull(diffList, "diffList");

        this.diffList = diffList;
        this.lhs = lhs;
        this.rhs = rhs;

        if (style == null) {
            this.style = ToStringStyle.DEFAULT_STYLE;
        } else {
            this.style = style;
        }
    }

    public T getLeft() {
        return this.lhs;
    }

    public T getRight() {
        return this.rhs;
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(diffList);
    }

    public int getNumberOfDiffs() {
        return diffList.size();
    }

    /**
     * <p>
     * Returns the style used by the {@link #toString()} method.
     * </p>
     *
     * @return the style
     */
    public ToStringStyle getToStringStyle() {
        return style;
    }


    @Override
    public String toString() {
        return toString(style);
    }

    public String toString(final ToStringStyle style) {
        if (diffList.isEmpty()) {
            return OBJECTS_SAME_STRING;
        }

        final ToStringBuilder lhsBuilder = new ToStringBuilder(lhs, style);
        final ToStringBuilder rhsBuilder = new ToStringBuilder(rhs, style);

        for (final Diff<?> diff : diffList) {
            lhsBuilder.append(diff.getFieldName(), diff.getLeft());
            rhsBuilder.append(diff.getFieldName(), diff.getRight());
        }

        return String.format("%s %s %s", lhsBuilder.build(), DIFFERS_STRING,
                rhsBuilder.build());
    }

    /**
     * <p>
     * Returns an iterator over the {@code Diff} objects contained in this list.
     * </p>
     *
     * @return the iterator
     */
    @Override
    public Iterator<Diff<?>> iterator() {
        return diffList.iterator();
    }
}
