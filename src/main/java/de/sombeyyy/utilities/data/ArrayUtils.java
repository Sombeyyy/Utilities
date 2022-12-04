package de.sombeyyy.utilities.data;

import de.sombeyyy.utilities.relfection.ClassUtils;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;

public final class ArrayUtils {

    public static final Map<Class<?>, Object> EMPTY_ARRAY_MAP;

    static {

        EMPTY_ARRAY_MAP = Map.ofEntries(Map.entry(boolean.class, new boolean[0]),
                Map.entry(byte.class, new byte[0]),
                Map.entry(short.class, new short[0]),
                Map.entry(char.class, new char[0]),
                Map.entry(int.class, new int[0]),
                Map.entry(long.class, new long[0]),
                Map.entry(float.class, new float[0]),
                Map.entry(double.class, new double[0]),
                Map.entry(Void.class, new Void[0]),
                Map.entry(Boolean.class, new Boolean[0]),
                Map.entry(Byte.class, new Byte[0]),
                Map.entry(Short.class, new Short[0]),
                Map.entry(Character.class, new Character[0]),
                Map.entry(Integer.class, new Integer[0]),
                Map.entry(Long.class, new Long[0]),
                Map.entry(Float.class, new Float[0]),
                Map.entry(Double.class, new Double[0]));
    }

    private ArrayUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static boolean isNullOrEmpty(final Object array) {
        return array == null || Array.getLength(array) == 0;
    }

    public static boolean exists(final Object array) {
        return !isNullOrEmpty(array);
    }

    public static Object wrap(Object array) {
        if (array == null) {
            return null;
        }

        // When parameter is not array, returns as it is.
        Class<?> componentType = array.getClass().getComponentType();
        if (componentType == null) {
            return array;
        }

        int length = Array.getLength(array);
        Class<?> wrapped = ClassUtils.wrap(componentType);
        Object wrappedArray = Array.newInstance(wrapped, length);

        for (int i = 0; i < length; i++) {
            // If element is primitive, it is already auto-boxed by Array.get(Object, int).
            Object element = Array.get(array, i);

            // Primitive value is not allowed to be null, so the element is considered as Array.
            // When element is array, do again recursively.
            if (element == null || element.getClass().isArray()) {
                element = wrap(element);
            }

            Array.set(wrappedArray, i, element);
        }

        return wrappedArray;
    }

    public static Object unwrap(final Object array) {
        if (array == null) {
            return null;
        }

        // When parameter is not array, returns as it is.
        Class<?> componentType = array.getClass().getComponentType();
        if (componentType == null) {
            return array;
        }

        int length = Array.getLength(array);
        Class<?> primitive = ClassUtils.unwrap(componentType);
        Object primitiveArray = Array.newInstance(primitive, length);

        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);

            // Primitive value is not allowed to be null, so the element is considered as Array.
            // When element is array, do again recursively.
            if (element == null || element.getClass().isArray()) {
                element = unwrap(element);
            }

            // If component type of array is primitive,
            // the element will be auto-unboxed by Array.set(Object, int, Object).
            Array.set(primitiveArray, i, element);
        }

        return primitiveArray;
    }

    public static String toString(final Object array) {
        if (array == null) {
            return "null";
        }

        Class<?> clazz = array.getClass();
        if (clazz.isArray()) {
            // One dimensional object array or multi-dimensional array.
            if (array instanceof Object[]) return Arrays.deepToString((Object[]) array);

            // One dimensional primitive array.
            Class<?> componentType = clazz.getComponentType();
            if (componentType == boolean.class) return Arrays.toString((boolean[]) array);
            if (componentType == byte.class) return Arrays.toString((byte[]) array);
            if (componentType == short.class) return Arrays.toString((short[]) array);
            if (componentType == char.class) return Arrays.toString((char[]) array);
            if (componentType == int.class) return Arrays.toString((int[]) array);
            if (componentType == long.class) return Arrays.toString((long[]) array);
            if (componentType == float.class) return Arrays.toString((float[]) array);
            if (componentType == double.class) return Arrays.toString((double[]) array);

        } else if (array instanceof Path) {
            // Must check it is implementation of Path before Iterable, or StackOverflowError is thrown.
            // Implementation of Iterable like Path returns true as the result of Iterable.hasNext()
            // even though it reaches to the end of elements.
            return array.toString();

        } else if (array instanceof Iterable) {
            Iterator<?> iterator = ((Iterable<?>) array).iterator();

            StringBuilder sb = new StringBuilder("[");
            if (iterator.hasNext()) {
                sb.append(toString(iterator.next()));

                while (iterator.hasNext()) {
                    sb.append(", ").append(toString(iterator.next()));
                }
            }
            sb.append("]");

            return sb.toString();

        } else if (array instanceof Map) {
            Iterator<? extends Map.Entry<?, ?>> iterator = ((Map<?, ?>) array).entrySet().iterator();

            StringBuilder sb = new StringBuilder("{");
            if (iterator.hasNext()) {
                Map.Entry<?, ?> e0 = iterator.next();
                sb.append(toString(e0.getKey())).append('=').append(toString(e0.getValue()));

                while (iterator.hasNext()) {
                    Map.Entry<?, ?> e1 = iterator.next();
                    sb.append(", ").append(toString(e1.getKey())).append('=').append(toString(e1.getValue()));
                }
            }
            sb.append("}");

            return sb.toString();
        }

        // Others.
        return array.toString();
    }

    public static int hashCode(final Object array) {
        if (array == null) return 0;

        Class<?> clazz = array.getClass();
        if (clazz.isArray()) {
            // One dimensional object array or multi-dimensional array.
            if (array instanceof Object[]) return Arrays.deepHashCode((Object[]) array);

            // One dimensional primitive array.
            Class<?> componentType = clazz.getComponentType();
            if (componentType == boolean.class) return Arrays.hashCode((boolean[]) array);
            if (componentType == byte.class) return Arrays.hashCode((byte[]) array);
            if (componentType == short.class) return Arrays.hashCode((short[]) array);
            if (componentType == char.class) return Arrays.hashCode((char[]) array);
            if (componentType == int.class) return Arrays.hashCode((int[]) array);
            if (componentType == long.class) return Arrays.hashCode((long[]) array);
            if (componentType == float.class) return Arrays.hashCode((float[]) array);
            if (componentType == double.class) return Arrays.hashCode((double[]) array);
        }

        return array.hashCode();
    }

    public static Object[] prepend(final Object[] src, final Object... elements) {
        if (isNullOrEmpty(elements)) return src;

        Object[] array = new Object[elements.length + src.length];
        System.arraycopy(elements, 0, array, 0, elements.length);
        if (exists(src)) System.arraycopy(src, 0, array, elements.length, src.length);

        return array;
    }

    public static Object[] append(final Object[] src, final Object... elements) {
        if (isNullOrEmpty(elements)) return src;

        Object[] array = new Object[src.length + elements.length];
        if (exists(src)) System.arraycopy(src, 0, array, 0, src.length);
        System.arraycopy(elements, 0, array, src.length, elements.length);

        return array;
    }

    public static Class<?> resolveArrayType(Class<?> type, int dimension) {
        for (int i = 0; i < dimension; i++) {
            type = Array.newInstance(type, 0).getClass();
        }

        return type;
    }

    public static Class<?> resolveActualComponentType(Class<?> type) {
        while (type.isArray()) {
            type = type.getComponentType();
        }

        return type;
    }

    public static int dimensionOf(Class<?> type) {
        int dimension = 0;
        while (type.isArray()) {
            type = type.getComponentType();
            dimension++;
        }

        return dimension;
    }

}
