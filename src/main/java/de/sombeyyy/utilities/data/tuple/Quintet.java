package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.*;
import org.jetbrains.annotations.NotNull;

public class Quintet<T1, T2, T3, T4, T5> extends Tuple implements IValueOne, IValueTwo, IValueThree, IValueFour, IValueFive {

    private static final int DIMENSION = 5;

    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;
    private final T5 fifth;

    protected Quintet(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                      @NotNull final T4 fourth, @NotNull final T5 fifth) {
        super(first, second, third, fourth, fifth);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
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
    public T4 getFourth() {
        return fourth;
    }

    @NotNull
    @Override
    public T5 getFifth() {
        return fifth;
    }

    @NotNull
    @Override
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Sextet<T1, T2, T3, T4, T5, X> add(@NotNull final X sixth) {
        if (sixth == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Sextet<>(first, second, third, fourth, fifth, sixth);
    }
}
