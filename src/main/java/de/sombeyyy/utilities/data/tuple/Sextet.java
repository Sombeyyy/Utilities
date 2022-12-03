package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.*;
import org.jetbrains.annotations.NotNull;

public class Sextet<T1, T2, T3, T4, T5, T6> extends Tuple implements IValueOne, IValueTwo, IValueThree, IValueFour, IValueFive, IValueSix {

    private static final int DIMENSION = 6;

    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;
    private final T5 fifth;
    private final T6 sixth;

    protected Sextet(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                     @NotNull final T4 fourth, @NotNull final T5 fifth, @NotNull final T6 sixth) {
        super(first, second, third, fourth, fifth, sixth);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
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
    public T6 getSixth() {
        return sixth;
    }

    @NotNull
    @Override
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Septet<T1, T2, T3, T4, T5, T6, X> add(@NotNull final X seventh) {
        if (seventh == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Septet<>(first, second, third, fourth, fifth, sixth, seventh);
    }

}
