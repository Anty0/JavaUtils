/*
 * Copyright 2017 Jiří Kuchyňka (Anty)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.codetopic.java.utils;

import java.util.Comparator;

/**
 * Backport of java.util.Objects
 * Utility methods for objects.
 */
public final class Objects {
    private Objects() {
    }

    /**
     * Returns 0 if {@code a == b}, or {@code c.compare(a, b)} otherwise.
     * That is, this makes {@code c} null-safe.
     */
    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        if (a == b) {
            return 0;
        }
        return c.compare(a, b);
    }

    /**
     * Returns true if both arguments are null,
     * the result of {@link java.util.Arrays#equals} if both arguments are primitive arrays,
     * the result of {@link java.util.Arrays#deepEquals} if both arguments are arrays of reference types,
     * and the result of {@link #equals} otherwise.
     */
    public static boolean deepEquals(Object a, Object b) {
        if (a == null || b == null) {
            return a == b;
        } else if (a instanceof Object[] && b instanceof Object[]) {
            return java.util.Arrays.deepEquals((Object[]) a, (Object[]) b);
        } else if (a instanceof boolean[] && b instanceof boolean[]) {
            return java.util.Arrays.equals((boolean[]) a, (boolean[]) b);
        } else if (a instanceof byte[] && b instanceof byte[]) {
            return java.util.Arrays.equals((byte[]) a, (byte[]) b);
        } else if (a instanceof char[] && b instanceof char[]) {
            return java.util.Arrays.equals((char[]) a, (char[]) b);
        } else if (a instanceof double[] && b instanceof double[]) {
            return java.util.Arrays.equals((double[]) a, (double[]) b);
        } else if (a instanceof float[] && b instanceof float[]) {
            return java.util.Arrays.equals((float[]) a, (float[]) b);
        } else if (a instanceof int[] && b instanceof int[]) {
            return java.util.Arrays.equals((int[]) a, (int[]) b);
        } else if (a instanceof long[] && b instanceof long[]) {
            return java.util.Arrays.equals((long[]) a, (long[]) b);
        } else if (a instanceof short[] && b instanceof short[]) {
            return java.util.Arrays.equals((short[]) a, (short[]) b);
        }
        return a.equals(b);
    }

    /**
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * Convenience wrapper for {@link java.util.Arrays#hashCode}, adding varargs.
     * This can be used to compute a hash code for an object's fields as follows:
     * {@code Objects.hash(a, b, c)}.
     */
    public static int hash(Object... values) {
        return java.util.Arrays.hashCode(values);
    }

    /**
     * Returns 0 for null or {@code o.hashCode()}.
     */
    public static int hashCode(Object o) {
        return (o == null) ? 0 : o.hashCode();
    }

    /**
     * Returns {@code o} if non-null, or throws {@code NullPointerException}.
     */
    public static <T> T requireNonNull(T o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return o;
    }

    /**
     * Returns {@code o} if non-null, or throws {@code NullPointerException}
     * with the given detail message.
     */
    public static <T> T requireNonNull(T o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }

    /**
     * Returns "null" for null or {@code o.toString()}.
     */
    public static String toString(Object o) {
        return (o == null) ? "null" : o.toString();
    }

    /**
     * Returns {@code nullString} for null or {@code o.toString()}.
     */
    public static String toString(Object o, String nullString) {
        return (o == null) ? nullString : o.toString();
    }
}