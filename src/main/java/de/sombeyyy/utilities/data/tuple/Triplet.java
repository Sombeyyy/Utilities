package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.IValueOne;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueThree;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueTwo;
import org.jetbrains.annotations.NotNull;

public class Triplet<T1, T2, T3> extends Tuple implements IValueOne, IValueTwo, IValueThree {

    private static final int DIMENSION = 3;

    private final T1 first;
    private final T2 second;
    private final T3 third;

    protected Triplet(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third) {
        super(first, second, third);
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @NotNull
    @Override
    public T1 getFirst() {
        return first;
    }

    @NotNull
    @Override
    public T2 getSecond() {
        return second;
    }

    @NotNull
    @Override
    public T3 getThird() {
        return third;
    }

    @NotNull
    @Override
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Quartet<T1, T2, T3, X> add(@NotNull final X fourth) {
        if (fourth == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Quartet<>(first, second, third, fourth);
    }

}
