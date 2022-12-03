package de.sombeyyy.utilities.data.tuple;

import de.sombeyyy.utilities.data.tuple.Tuple;
import org.jetbrains.annotations.NotNull;

public class Unit extends Tuple {

    private static final int DIMENSION = 0;

    protected Unit() {
        super();
    }

    @Override
    public int getDimension() {
        return DIMENSION;
    }

    public <X> Singleton<X> add(@NotNull final X first) {
        if (first == null) throw new IllegalArgumentException("Value for tuple cannot be null.");
        return new Singleton<>(first);
    }

}
