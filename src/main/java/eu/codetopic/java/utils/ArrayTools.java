package eu.codetopic.java.utils;

import java.util.Arrays;

public final class ArrayTools {

    private ArrayTools() {
    }

    public static <T> boolean contains(T[] array, T content) {
        for (T t : array) {
            if (Objects.equals(t, content)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] array, int content) {
        for (int t : array) {
            if (Objects.equals(t, content)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] add(T[] array, int index, T content) {
        T[] output = Arrays.copyOf(array, array.length + 1);
        System.arraycopy(output, index, output, index + 1, array.length - index);
        output[index] = content;
        return output;
    }

    public static <T> T[] add(T[] array, T content) {
        return add(array, array.length, content);
    }

    public static <T> T[] addAll(T[] array, T[] content) {
        T[] output = Arrays.copyOf(array, array.length + content.length);
        System.arraycopy(content, 0, output, array.length, content.length);
        return output;
    }

    public static <T> T[] remove(T[] array, int index) {
        if (index == 0) {
            return Arrays.copyOfRange(array, 1, array.length);
        } else if (index == array.length - 1) {
            return Arrays.copyOfRange(array, 0, index);
        } else {
            return addAll(Arrays.copyOfRange(array, 0, index),
                    Arrays.copyOfRange(array, index + 1, array.length));
        }
    }
}
