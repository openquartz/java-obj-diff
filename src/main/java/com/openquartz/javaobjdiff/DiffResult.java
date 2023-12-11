package com.openquartz.javaobjdiff;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.tuple.Pair;

/**
 * DiffResult
 *
 * @param <T> T
 * @author svnee
 */
public class DiffResult<T> implements Iterable<Diff<?>> {

    public static final String OBJECTS_SAME_STRING = "";

    private static final String DIFFERS_STRING = "->";

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

        this.style = Objects.requireNonNullElse(style, ToStringStyle.DEFAULT_STYLE);
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

        Pair<ToStringBuilder, ToStringBuilder> stringBuilderPair = toStringBuilder();

        if (stringBuilderPair == null) {
            return OBJECTS_SAME_STRING;
        }

        return String.format("%s %s %s", stringBuilderPair.getLeft().build(), DIFFERS_STRING,
            stringBuilderPair.getRight().build());
    }

    private Pair<ToStringBuilder, ToStringBuilder> toStringBuilder() {

        if (diffList.isEmpty()) {
            return null;
        }

        final ToStringBuilder lhsBuilder = new ToStringBuilder(lhs, style);
        final ToStringBuilder rhsBuilder = new ToStringBuilder(rhs, style);

        for (final Diff<?> diff : diffList) {
            lhsBuilder.append(diff.getFieldName(), diff.getLeft());
            rhsBuilder.append(diff.getFieldName(), diff.getRight());
        }

        return Pair.of(lhsBuilder, rhsBuilder);
    }

    /**
     * 是否存在不同
     */
    public boolean isDiff() {
        return !diffList.isEmpty();
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
