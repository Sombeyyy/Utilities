package de.sombeyyy.utilities.data.tuple;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

public abstract class Tuple implements Iterable<Object>, Serializable, Comparable<Tuple> {

    public static <T1> Singleton<T1> of(@NotNull final T1 first) {
        if (validate(first)) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Singleton<>(first);
    }

    public static <T1, T2> Pair<T1, T2> of(@NotNull final T1 first, @NotNull final T2 second) {
        if (validate(first, second)) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Pair<>(first, second);
    }

    public static <T1, T2, T3> Triplet<T1, T2, T3> of(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third) {
        if (validate(first, second, third)) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Triplet<>(first, second, third);
    }

    public static <T1, T2, T3, T4> Quartet<T1, T2, T3, T4> of(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                                                              @NotNull final T4 fourth) {
        if (validate(first, second, third, fourth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Quartet<>(first, second, third, fourth);
    }

    public static <T1, T2, T3, T4, T5> Quintet<T1, T2, T3, T4, T5> of(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                                                                      @NotNull final T4 fourth, @NotNull final T5 fifth) {
        if (validate(first, second, third, fourth, fifth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Quintet<>(first, second, third, fourth, fifth);
    }

    public static <T1, T2, T3, T4, T5, T6> Sextet<T1, T2, T3, T4, T5, T6> of(@NotNull final T1 first, @NotNull final T2 second,
                                                                             @NotNull final T3 third, @NotNull final T4 fourth,
                                                                             @NotNull final T5 fifth, @NotNull final T6 sixth) {
        if (validate(first, second, third, fourth, fifth, sixth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Sextet<>(first, second, third, fourth, fifth, sixth);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Septet<T1, T2, T3, T4, T5, T6, T7> of(@NotNull final T1 first, @NotNull final T2 second,
                                                                                     @NotNull final T3 third, @NotNull final T4 fourth,
                                                                                     @NotNull final T5 fifth, @NotNull final T6 sixth,
                                                                                     @NotNull final T7 seventh) {
        if (validate(first, second, third, fourth, fifth, sixth, seventh))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Septet<>(first, second, third, fourth, fifth, sixth, seventh);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Octet<T1, T2, T3, T4, T5, T6, T7, T8> of(@NotNull final T1 first, @NotNull final T2 second,
                                                                                            @NotNull final T3 third, @NotNull final T4 fourth,
                                                                                            @NotNull final T5 fifth, @NotNull final T6 sixth,
                                                                                            @NotNull final T7 seventh, @NotNull final T8 eighth) {
        if (validate(first, second, third, fourth, fifth, sixth, seventh, eighth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Octet<>(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Ennead<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(@NotNull final T1 first,
                                                                                                     @NotNull final T2 second,
                                                                                                     @NotNull final T3 third,
                                                                                                     @NotNull final T4 fourth,
                                                                                                     @NotNull final T5 fifth,
                                                                                                     @NotNull final T6 sixth,
                                                                                                     @NotNull final T7 seventh,
                                                                                                     @NotNull final T8 eighth,
                                                                                                     @NotNull final T9 ninth) {
        if (validate(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Ennead<>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Decade<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> of(@NotNull final T1 first,
                                                                                                               @NotNull final T2 second,
                                                                                                               @NotNull final T3 third,
                                                                                                               @NotNull final T4 fourth,
                                                                                                               @NotNull final T5 fifth,
                                                                                                               @NotNull final T6 sixth,
                                                                                                               @NotNull final T7 seventh,
                                                                                                               @NotNull final T8 eighth,
                                                                                                               @NotNull final T9 ninth,
                                                                                                               @NotNull final T10 tenth) {
        if (validate(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth))
            throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Decade<>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
    }

    private static boolean validate(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) return true;
        }
        return false;
    }

    private final Object[] valueArray;

    protected Tuple(final Object... values) {
        if (values.length > 10) {
            throw new IllegalArgumentException("Cannot create Tuple with " + values.length + " values. Max amount is 10");
        }
        this.valueArray = values;
    }

    public abstract int getDimension();

    public final Object getValue(@NotNull final int pos) {
        if (pos >= getDimension() || pos < 0) {
            throw new IllegalArgumentException("Cannot retrieve position " + pos + " in " + this.getClass().getSimpleName()
                    + ". Position for this class start with 0 and end with " + (getDimension() - 1));
        }
        return this.valueArray[pos];
    }

    @Override
    public final Iterator<Object> iterator() {
        return Arrays.stream(valueArray).iterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(valueArray);
    }

    public final boolean contains(@NotNull final Object value) {
        return Arrays.asList(valueArray).contains(value);
    }

    public final boolean containsAll(@NotNull final Collection<?> collection) {
        return Arrays.asList(valueArray).containsAll(collection);
    }

    public final boolean containsAll(@NotNull final Object... values) {
        return Arrays.asList(valueArray).containsAll(Arrays.asList(values));
    }

    public final int indexOf(@NotNull final Object value) {
        return Arrays.binarySearch(valueArray, value);
    }

    public final int lastIndexOf(@NotNull final Object value) {
        return Arrays.asList(valueArray).lastIndexOf(value);
    }

    @NotNull
    public final List<Object> asList() {
        return List.of(valueArray);
    }

    @NotNull
    public final Object[] asArray() {
        return Arrays.copyOf(valueArray, valueArray.length);
    }

    @Override
    public int hashCode() {
        return valueArray.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Tuple other = (Tuple) obj;
        return this.valueArray.equals(valueArray);
    }

    @NotNull
    @Override
    public int compareTo(@NotNull Tuple o) {
        for (int i = 0; i < valueArray.length && i < o.valueArray.length; i++) {
            int result = ((Comparable) valueArray[i]).compareTo(((Comparable) o.valueArray[i]));
            if (result != 0) return result;
        }
        return Integer.valueOf(valueArray.length).compareTo(Integer.valueOf(o.valueArray.length));
    }

}