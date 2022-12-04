package de.sombeyyy.utilities.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberUtils {

    private static final NumberFormat formatter = NumberFormat.getInstance(Locale.US);

    static {
        formatter.setGroupingUsed(false);
    }

    private NumberUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static Double toPositive(final Double number) {
        return number == null || number <= 0.0 ? Double.valueOf(1.0) : number;
    }

    public static Float toPositive(final Float number) {
        return number == null || number <= 0.0F ? Float.valueOf(1.0F) : number;
    }

    public static Long toPositive(final Long number) {
        return number == null || number <= 0L ? Long.valueOf(1L) : number;
    }

    public static Integer toPositive(final Integer number) {
        return number == null || number <= 0 ? Integer.valueOf(number) : number;
    }

    public static Short toPositive(final Short number) {
        return number == null || number <= 0 ? Short.valueOf((short) 1) : number;
    }

    public static Byte toPositive(final Byte number) {
        return number == null || number <= 0 ? Byte.valueOf((byte) 1) : number;
    }

    public static Double toNegative(final Double number) {
        return number == null || number >= 0.0 ? Double.valueOf(-1.0) : number;
    }

    public static Float toNegative(final Float number) {
        return number == null || number >= 0.0F ? Float.valueOf(-1.0F) : number;
    }

    public static Long toNegative(final Long number) {
        return number == null || number >= 0L ? Long.valueOf(-1L) : number;
    }

    public static Integer toNegative(final Integer number) {
        return number == null || number >= 0 ? Integer.valueOf(-1) : number;
    }

    public static Short toNegative(final Short number) {
        return number == null || number >= 0 ? Short.valueOf((short) -1) : number;
    }

    public static Byte toNegative(final Byte number) {
        return number == null || number >= 0 ? Byte.valueOf((byte) -1) : number;
    }

    public static Double ifNull(final Double number, final double defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static Float ifNull(final Float number, final float defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static Long ifNull(final Long number, final long defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static Integer ifNull(final Integer number, final int defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static Short ifNull(final Short number, final short defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static Byte ifNull(final Byte number, final byte defaultNumber) {
        return number == null ? defaultNumber : number;
    }

    public static String format(final double decimal) {
        return formatter.format(decimal);
    }

    public static int getNumOfPlaces(long number) {
        if (number == Long.MIN_VALUE) number++;
        return number == 0 ? 1 : (int) (Math.log10(Math.abs(number)) + 1);
    }

    public static int getNumOfPlaces(final BigInteger bigInt) {
        return bigInt.abs().toString().length();
    }

    public static boolean hasDecimalPart(final double number) {
        return number % 1 != 0;
    }

    public static boolean hasDecimalPart(final BigDecimal bigDecimal) {
        return bigDecimal.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0;
    }

    public static long reverse(long number) {
        long reversed = 0;

        // Runs loop until number becomes 0.
        while (number != 0) {

            // Gets last digit from number.
            long digit = number % 10;
            reversed = reversed * 10 + digit;

            // Removes the last digit from number.
            number /= 10;
        }

        return reversed;
    }

    public static BigInteger reverse(BigInteger bigInt) {
        BigInteger reversed = BigInteger.ZERO;

        // Runs loop until number becomes 0.
        while (!bigInt.equals(BigInteger.ZERO)) {
            // Gets last digit from number.
            BigInteger digit = bigInt.remainder(BigInteger.TEN);
            reversed = reversed.multiply(BigInteger.TEN).add(digit);

            // Removes the last digit from number.
            bigInt = bigInt.divide(BigInteger.TEN);
        }

        return reversed;
    }

}