package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.*;
import org.jetbrains.annotations.NotNull;

public class Decade<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> extends Tuple implements IValueOne, IValueTwo, IValueThree, IValueFour, IValueFive, IValueSix, IValueSeven, IValueEight, IValueNine, IValueTen {

    private static final int DIMENSION = 10;

    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;
    private final T5 fifth;
    private final T6 sixth;
    private final T7 seventh;
    private final T8 eighth;
    private final T9 ninth;
    private final T10 tenth;

    protected Decade(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                     @NotNull final T4 fourth, @NotNull final T5 fifth, @NotNull final T6 sixth,
                     @NotNull final T7 seventh, @NotNull final T8 eighth, @NotNull final T9 ninth,
                     @NotNull final T10 tenth) {
        super(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.ninth = ninth;
        this.tenth = tenth;
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
    public T7 getSeventh() {
        return seventh;
    }

    @NotNull
    @Override
    public T8 getEighth() {
        return eighth;
    }

    @NotNull
    @Override
    public T9 getNinth() {
        return ninth;
    }

    @NotNull
    @Override
    public T10 getTenth() {
        return tenth;
    }

    @NotNull
    @Override
    public int getDimension() {
        return DIMENSION;
    }
}
