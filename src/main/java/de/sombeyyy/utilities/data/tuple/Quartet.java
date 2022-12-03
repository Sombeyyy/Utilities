package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.IValueFour;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueOne;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueThree;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueTwo;
import org.jetbrains.annotations.NotNull;

public class Quartet<T1, T2, T3, T4> extends Tuple implements IValueOne, IValueTwo, IValueThree, IValueFour {

    private static final int DIMENSION = 4;

    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;

    protected Quartet(@NotNull final T1 first, @NotNull final T2 second, @NotNull final T3 third,
                      @NotNull final T4 fourth) {
        super(first, second, third, fourth);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
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
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Quintet<T1, T2, T3, T4, X> add(@NotNull final X fifth) {
        if (fifth == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Quintet<>(first, second, third, fourth, fifth);
    }

}
