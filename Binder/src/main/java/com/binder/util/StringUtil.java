package com.binder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String util.
 */
public class StringUtil {


    public static final String[] EMPTY_ARRAY = new String[0];


    /**
     * Is blank.
     *
     * @param str
     * @return
     */
    public static boolean isBlank(CharSequence str) {
        if ((str == null)) {
            return true;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            boolean charNotBlank = Character.isWhitespace(c) || Character.isSpaceChar(c) || c == '\ufeff' || c == '\u202a';
            if (!charNotBlank) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is empty.
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }


    /**
     * To symbol case.
     *
     * @param str
     * @param symbol
     * @return
     */
    public static String toSymbolCase(CharSequence str, char symbol) {
        if (str == null) {
            return null;
        }

        final int length = str.length();
        final StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
            if (Character.isUpperCase(c)) {
                final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
                if (null != preChar && Character.isUpperCase(preChar)) {
                    sb.append(c);
                } else if (null != nextChar && Character.isUpperCase(nextChar)) {
                    if (null != preChar && symbol != preChar) {
                        sb.append(symbol);
                    }
                    sb.append(c);
                } else {
                    if (null != preChar && symbol != preChar) {
                        sb.append(symbol);
                    }
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                if (sb.length() > 0 && Character.isUpperCase(sb.charAt(sb.length() - 1)) && symbol != c) {
                    sb.append(symbol);
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * to camel case
     *
     * @param str    CharSequence
     * @param symbol symbol
     * @return toCamelCase String
     */
    public static String toCamelCase(CharSequence str, char symbol) {
        if (null == str || str.length() == 0) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        boolean upperCase = false;
        for (int i = 0; i < length; ++i) {
            char c = str.charAt(i);
            if (c == symbol) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Replace a portion of the string, replacing all found
     *
     * @param str        A string to operate on
     * @param searchStr  The replaced string
     * @param replaceStr The replaced string
     * @return Replace the result
     */
    public static String replace(String str, String searchStr, String replaceStr) {
        return Pattern
                .compile(searchStr, Pattern.LITERAL)
                .matcher(str)
                .replaceAll(Matcher.quoteReplacement(replaceStr));
    }

    /**
     * <p>Splits the provided text into an array, separators specified.
     *
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     * @param str the String to parse, may be null
     * @param separatorChars the characters used as the delimiters,
     * @return an array of parsed Strings
     */
    public static String[] split(final String str, final String separatorChars) {
        if (str == null) {
            return null;
        }
        if (isBlank(str)) {
            return EMPTY_ARRAY;
        }
        if (isBlank(separatorChars)) {
            return str.split(" ");
        }
        return str.split(separatorChars);
    }

    /**
     * Tests if this string starts with the specified prefix.
     *
     * @param str    this str
     * @param prefix the suffix
     * @return Whether the prefix exists
     */
    public static boolean startWith(String str, String prefix) {
        if (isEmpty(str)) {
            return false;
        }
        return str.startsWith(prefix);
    }
}
