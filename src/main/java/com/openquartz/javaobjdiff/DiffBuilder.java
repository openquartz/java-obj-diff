package com.openquartz.javaobjdiff;

import com.openquartz.javaobjdiff.annotation.DiffCompare;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class DiffBuilder<T> implements Builder<DiffResult<T>> {

    private final List<Diff<?>> diffs;
    private final boolean objectsTriviallyEqual;
    private final T left;
    private final T right;
    private final ToStringStyle style;

    private static final Map<Class<? extends DiffComparable>, DiffComparable> diffComparableInstanceMap =
        new ConcurrentHashMap<>();

    public DiffBuilder(final T lhs, final T rhs, final ToStringStyle style, final boolean testTriviallyEqual) {

        Validate.notNull(lhs, "lhs");
        Validate.notNull(rhs, "rhs");

        this.diffs = new ArrayList<>();
        this.left = lhs;
        this.right = rhs;
        this.style = style;

        // Don't compare any fields if objects equal
        this.objectsTriviallyEqual = testTriviallyEqual && (lhs == rhs || lhs.equals(rhs));
    }

    public DiffBuilder(final T lhs, final T rhs, final ToStringStyle style) {

        this(lhs, rhs, style, true);
    }

    public DiffBuilder<T> append(final String fieldName, final boolean lhs, final boolean rhs) {

        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Boolean>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Boolean getLeft() {
                    return lhs;
                }

                @Override
                public Boolean getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final boolean[] lhs, final boolean[] rhs) {

        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }

        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Boolean[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Boolean[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Boolean[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final byte lhs,
        final byte rhs) {
        validateFieldNameNotNull(fieldName);
        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Byte>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Byte getLeft() {
                    return lhs;
                }

                @Override
                public Byte getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final byte[] lhs,
        final byte[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Byte[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Byte[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Byte[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final char lhs,
        final char rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Character>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Character getLeft() {
                    return lhs;
                }

                @Override
                public Character getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final char[] lhs,
        final char[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Character[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Character[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Character[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final double lhs,
        final double rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (Double.doubleToLongBits(lhs) != Double.doubleToLongBits(rhs)) {
            diffs.add(new Diff<Double>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Double getLeft() {
                    return lhs;
                }

                @Override
                public Double getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }


    public DiffBuilder<T> append(final String fieldName, final double[] lhs,
        final double[] rhs) {

        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Double[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Double[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Double[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    /**
     * <p>
     * Test if two {@code float}s are equal.
     * </p>
     *
     * @param fieldName the field name
     * @param lhs the left hand {@code float}
     * @param rhs the right hand {@code float}
     * @return this
     * @throws IllegalArgumentException if field name is {@code null}
     */
    public DiffBuilder<T> append(final String fieldName, final float lhs,
        final float rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (Float.floatToIntBits(lhs) != Float.floatToIntBits(rhs)) {
            diffs.add(new Diff<Float>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Float getLeft() {
                    return lhs;
                }

                @Override
                public Float getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    /**
     * <p>
     * Test if two {@code float[]}s are equal.
     * </p>
     *
     * @param fieldName the field name
     * @param lhs the left hand {@code float[]}
     * @param rhs the right hand {@code float[]}
     * @return this
     * @throws IllegalArgumentException if field name is {@code null}
     */
    public DiffBuilder<T> append(final String fieldName, final float[] lhs,
        final float[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Float[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Float[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Float[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    /**
     * <p>
     * Test if two {@code int}s are equal.
     * </p>
     *
     * @param fieldName the field name
     * @param lhs the left hand {@code int}
     * @param rhs the right hand {@code int}
     * @return this
     * @throws IllegalArgumentException if field name is {@code null}
     */
    public DiffBuilder<T> append(final String fieldName, final int lhs,
        final int rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Integer>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Integer getLeft() {
                    return lhs;
                }

                @Override
                public Integer getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    /**
     * <p>
     * Test if two {@code int[]}s are equal.
     * </p>
     *
     * @param fieldName the field name
     * @param lhs the left hand {@code int[]}
     * @param rhs the right hand {@code int[]}
     * @return this
     * @throws IllegalArgumentException if field name is {@code null}
     */
    public DiffBuilder<T> append(final String fieldName, final int[] lhs,
        final int[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Integer[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Integer[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Integer[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    /**
     * <p>
     * Test if two {@code long}s are equal.
     * </p>
     *
     * @param fieldName the field name
     * @param lhs the left hand {@code long}
     * @param rhs the right hand {@code long}
     * @return this
     * @throws IllegalArgumentException if field name is {@code null}
     */
    public DiffBuilder<T> append(final String fieldName, final long lhs,
        final long rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Long>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Long getLeft() {
                    return lhs;
                }

                @Override
                public Long getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final long[] lhs,
        final long[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Long[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Long[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Long[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final short lhs,
        final short rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs != rhs) {
            diffs.add(new Diff<Short>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Short getLeft() {
                    return lhs;
                }

                @Override
                public Short getRight() {
                    return rhs;
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final String fieldName, final short[] lhs,
        final short[] rhs) {
        validateFieldNameNotNull(fieldName);

        if (objectsTriviallyEqual) {
            return this;
        }
        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Short[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Short[] getLeft() {
                    return ArrayUtils.toObject(lhs);
                }

                @Override
                public Short[] getRight() {
                    return ArrayUtils.toObject(rhs);
                }
            });
        }
        return this;
    }

    public DiffBuilder<T> append(final Field field, final Object lhs,
        final Object rhs) {

        validateFieldNotNull(field);
        String fieldName = field.getName();
        if (objectsTriviallyEqual) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }

        DiffCompare diffCompare = field.getDeclaredAnnotation(DiffCompare.class);
        if (diffCompare != null) {

            DiffComparable diffComparable = getDiffComparator(diffCompare);
            if (diffComparable.diff(lhs, rhs)) {
                return this;
            }
            diffs.add(new Diff<Object>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Object getLeft() {
                    return lhs;
                }

                @Override
                public Object getRight() {
                    return rhs;
                }
            });

            return this;
        }

        // 处理 collection

        final Object objectToTest;
        if (lhs != null) {
            objectToTest = lhs;
        } else {
            // rhs cannot be null, as lhs != rhs
            objectToTest = rhs;
        }

        if (objectToTest.getClass().isArray()) {
            if (objectToTest instanceof boolean[]) {
                return append(fieldName, (boolean[]) lhs, (boolean[]) rhs);
            }
            if (objectToTest instanceof byte[]) {
                return append(fieldName, (byte[]) lhs, (byte[]) rhs);
            }
            if (objectToTest instanceof char[]) {
                return append(fieldName, (char[]) lhs, (char[]) rhs);
            }
            if (objectToTest instanceof double[]) {
                return append(fieldName, (double[]) lhs, (double[]) rhs);
            }
            if (objectToTest instanceof float[]) {
                return append(fieldName, (float[]) lhs, (float[]) rhs);
            }
            if (objectToTest instanceof int[]) {
                return append(fieldName, (int[]) lhs, (int[]) rhs);
            }
            if (objectToTest instanceof long[]) {
                return append(fieldName, (long[]) lhs, (long[]) rhs);
            }
            if (objectToTest instanceof short[]) {
                return append(fieldName, (short[]) lhs, (short[]) rhs);
            }

            return append(fieldName, (Object[]) lhs, (Object[]) rhs);
        }

        // Not array type
        if (lhs != null && lhs.equals(rhs)) {
            return this;
        }

        diffs.add(new Diff<Object>(fieldName) {
            private static final long serialVersionUID = 1L;

            @Override
            public Object getLeft() {
                return lhs;
            }

            @Override
            public Object getRight() {
                return rhs;
            }
        });

        return this;
    }

    private static DiffComparable getDiffComparator(DiffCompare diffCompare) {

        if (diffCompare == null) {
            return null;
        }

        Class<? extends DiffComparable> diffComparableClass = diffCompare.using();

        DiffComparable diffComparable = diffComparableInstanceMap.get(diffComparableClass);
        if (diffComparable != null) {
            return diffComparable;
        }

        try {
            DiffComparable instance = diffComparableClass.getDeclaredConstructor().newInstance();
            return diffComparableInstanceMap.putIfAbsent(diffComparableClass, instance);
        } catch (Exception ex) {
            return ExceptionUtils.rethrow(ex);
        }
    }

    public DiffBuilder<T> append(final String fieldName, final Object[] lhs,
        final Object[] rhs) {
        validateFieldNameNotNull(fieldName);
        if (objectsTriviallyEqual) {
            return this;
        }

        if (!Arrays.equals(lhs, rhs)) {
            diffs.add(new Diff<Object[]>(fieldName) {
                private static final long serialVersionUID = 1L;

                @Override
                public Object[] getLeft() {
                    return lhs;
                }

                @Override
                public Object[] getRight() {
                    return rhs;
                }
            });
        }

        return this;
    }

    public DiffResult<T> build() {
        return new DiffResult<>(left, right, diffs, style);
    }

    private void validateFieldNameNotNull(final String fieldName) {
        Validate.notNull(fieldName, "fieldName");
    }

    private void validateFieldNotNull(final Field field) {
        Validate.notNull(field, "field");
    }

}