package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.IValueOne;
import de.sombeyyy.utilities.data.tuple.ivalue.IValueTwo;
import org.jetbrains.annotations.NotNull;

public class Pair<T1, T2> extends Tuple implements IValueOne, IValueTwo {

    private static final int DIMENSION = 2;

    private final T1 first;
    private final T2 second;

    protected Pair(@NotNull T1 first, @NotNull T2 second) {
        super(first, second);
        this.first = first;
        this.second = second;
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
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Triplet<T1, T2, X> add(@NotNull final X third) {
        if (third == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Triplet<>(first, second, third);
    }

}
