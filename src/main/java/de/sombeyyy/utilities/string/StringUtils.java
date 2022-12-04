package de.sombeyyy.utilities.string;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    private static final char WHITE_SPACE = '\u0020';
    private static final NumberFormat formatter = NumberFormat.getInstance(Locale.US);

    private StringUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static boolean isNullOrEmpty(final String str) {
        return str == null || str.isEmpty();
    }

    public static String ifNullOrEmpty(final String str, final String _default) {
        return isNullOrEmpty(str) ? _default : str;
    }

    public static String ifNullOrEmpty(final String str, final Supplier<String> supplier) {
        return isNullOrEmpty(str) ? supplier.get() : str;
    }

    public static boolean isNullOrBlank(final String str) {
        if (isNullOrEmpty(str)) return true;

        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    public static String ifNullOrBlank(final String str, final String _default) {
        return isNullOrBlank(str) ? _default : str;
    }

    public static String ifNullOrBlank(final String str, final Supplier<String> supplier) {
        return isNullOrBlank(str) ? supplier.get() : str;
    }

    public static boolean anyEquals(final String criterion, Collection<String> strings) {
        //TODO: Implement
        return false;
    }

    public static boolean anyContains(final String criterion, final Iterable<String> strings) {
        if (criterion == null || strings == null || strings.spliterator().estimateSize() == 0) return false;

        for (String str : strings) {
            if (str != null && criterion.contains(str)) return true;
        }

        return false;
    }

    public static boolean isNumeric(final String str) {
        if (isNullOrEmpty(str)) return false;

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static String padStart(final int len, final String origin) {
        return padStart(len, origin, String.valueOf(WHITE_SPACE));
    }

    public static String repeat(final String str, int count) {
        return String.join("", Collections.nCopies(count, str));
    }

    public static String repeat(final char c, final int count) {
        return repeat(String.valueOf(c), count);
    }

    public static String padStart(final int len, final String origin, final String appendix) {
        int originLen = origin.length();
        if (originLen >= len) return origin;
        return repeat(appendix, len - originLen) + origin;
    }

    public static String padEnd(final int len, final String origin) {
        return padEnd(len, origin, String.valueOf(WHITE_SPACE));
    }

    public static String padEnd(final int len, final String origin, final String appendix) {
        int originLen = origin.length();
        if (originLen >= len) return origin;
        return origin + repeat(appendix, len - originLen);
    }

    public static int countOf(final String str, final String keyword) {
        if (keyword.isEmpty()) return str.length();
        int keywordLen = keyword.length();
        int count = 0;

        for (int i = str.indexOf(keyword); i >= 0; i = str.indexOf(keyword, i + keywordLen)) {
            count++;
        }

        return count;
    }

    public static String reverse(final String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String replaceLast(final String txt, final String regex, final String replacement) {
        return txt.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static String formatComma(final double amount) {
        return formatter.format(amount);
    }

    public static String formatComma(final String amount) {
        return formatter.format(Double.parseDouble(amount));
    }

    public static String find(final String str, final String regex, final int group) {
        return find(str, Pattern.compile(regex), group);
    }

    public static String find(final String str, final Pattern pattern, final int group) {
        Matcher matcher = pattern.matcher(str);

        String result = null;
        while (matcher.find()) {
            result = matcher.group(group);
        }
        return result;
    }

    public static Map<Integer, String> find(final String str, final String regex, final int flags, final int... groups) {
        return find(str, Pattern.compile(regex, flags), groups);
    }

    public static Map<Integer, String> find(final String str, final Pattern pattern, final int... groups) {
        Matcher matcher = pattern.matcher(str);
        Map<Integer, String> result = new HashMap<>();
        while (matcher.find()) {
            for (int group : groups) {
                result.put(group, matcher.group(group));
            }
        }
        return result;
    }

    public static String chop(final String str) {
        return str.isEmpty() ? str : str.substring(0, str.length() - 1);
    }

    public static String getLastString(final String str) {
        return str.isEmpty() ? str : String.valueOf(str.charAt(str.length() - 1));
    }

    public static int ordinalIndexOf(final String str, final char ch, final int ordinal) {
        // When ordinal is zero, regard as not finding.
        if (ordinal == 0) return -1;

        int index;

        if (ordinal > 0) {
            // When ordinal is positive, find the character from the beginning.
            index = str.indexOf(ch);
            if (index == -1) return -1;

            for (int i = 1; i < ordinal && index != -1; i++) {
                index = str.indexOf(ch, index + 1);
            }

        } else {
            // When ordinal is negative, find the character from the end.
            index = str.lastIndexOf(ch);
            if (index == -1) return -1;

            for (int i = -1; i > ordinal && index != -1; i--) {
                index = str.lastIndexOf(ch, index - 1);
            }
        }

        return index;
    }

    public static int indexOfCurrentClosingBracket(final String str, int pos, final char opener, final char closer) {
        if (isNullOrEmpty(str)) return -1;

        // Finds the opening bracket in the current context.
        char ch = str.charAt(pos);
        if (ch != opener) {
            // Prevents this variable from increasing when start character is closer.
            int depth = ch == closer ? 0 : 1;

            for (int i = pos; i >= 0; i--) {
                char c = str.charAt(i);

                if (c == closer) depth++;
                if (c == opener) {
                    depth--;
                    if (depth == 0) {
                        pos = i;
                        ch = str.charAt(pos);
                        break;
                    }
                }
            }

            // When not found opening bracket in whole characters.
            if (ch != opener) return -1;
        }

        // Since the opener is found, this variable will increase by 1 immediately.
        int depth = 0;

        for (int i = pos; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == opener) depth++;
            if (c == closer) {
                depth--;
                if (depth == 0) return i;
            }
        }

        // When not found the current closing bracket.
        return -1;
    }

}
