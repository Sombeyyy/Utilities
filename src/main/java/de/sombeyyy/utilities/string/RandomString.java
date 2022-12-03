package de.sombeyyy.utilities.string;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class RandomString {

    @NotNull
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    public static final String DIGITS = "0123456789";

    public static final String ALPHANUM = UPPER + LOWER + DIGITS;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomString(@NotNull int length, @NotNull Random random, @NotNull String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = random;
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    public RandomString(@NotNull int length, @NotNull Random random) {
        this(length, random, ALPHANUM);
    }

    public RandomString(@NotNull int length) {
        this(length, new SecureRandom());
    }

    public RandomString() {
        this(21);
    }

}
