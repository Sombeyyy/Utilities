package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.ivalue.IValueOne;
import org.jetbrains.annotations.NotNull;

public class Singleton<T1> extends Tuple implements IValueOne {

    private static final int DIMENSION = 1;

    private final T1 first;

    protected Singleton(@NotNull T1 first) {
        super(first);
        this.first = first;
    }

    @NotNull
    @Override
    public T1 getFirst() {
        return first;
    }

    @NotNull
    @Override
    public int getDimension() {
        return DIMENSION;
    }

    @NotNull
    public <X> Pair<T1, X> add(@NotNull final X second) {
        if (second == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Pair<>(first, second);
    }

}
