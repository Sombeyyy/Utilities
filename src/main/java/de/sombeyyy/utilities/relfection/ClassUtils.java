package de.sombeyyy.utilities.relfection;

import de.sombeyyy.utilities.data.ArrayUtils;

import java.lang.reflect.*;
import java.util.*;

public final class ClassUtils {

    private ClassUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static boolean isEnumOrEnumConstant(final Class<?> clazz) {
        return clazz != null && Enum.class.isAssignableFrom(clazz);
    }

    public static boolean isAbstractClass(final Class<?> clazz) {
        if (clazz == null) return false;

        //Keyword 'abstract' cannot be compatible with 'final' except native objects
        final int modifiers = clazz.getModifiers();
        return Modifier.isAbstract(modifiers) && !Modifier.isFinal(modifiers) && !clazz.isArray()
                && !clazz.isPrimitive() && !clazz.isInterface() && !isEnumOrEnumConstant(clazz);
    }

    public static boolean isWrapper(final Class<?> type) {
        return isNumericWrapper(type) || type == Boolean.class || type == Character.class || type == Void.class;
    }

    public static boolean isNumeric(final Class<?> type) {
        return isNumericPrimitive(type) || isNumericWrapper(type);
    }

    public static boolean isNumericPrimitive(final Class<?> type) {
        return type == byte.class || type == short.class || type == int.class ||
                type == long.class || type == float.class || type == double.class;
    }

    public static boolean isNumericWrapper(final Class<?> type) {
        return type == Byte.class || type == Short.class || type == Integer.class ||
                type == Long.class || type == Float.class || type == Double.class;
    }

    public static Object initialValueOf(final Class<?> type) {
        if (isNumericPrimitive(type)) return 0;
        if (type == char.class) return '\u0000';
        if (type == boolean.class) return false;
        return null;
    }

    public static <T> Class<T> wrap(final Class<T> type) {
        if (type == null) return null;

        Class<?> clazz = type;
        if (clazz == void.class) clazz = Void.class;
        if (clazz == boolean.class) clazz = Boolean.class;
        if (clazz == byte.class) clazz = Byte.class;
        if (clazz == short.class) clazz = Short.class;
        if (clazz == char.class) clazz = Character.class;
        if (clazz == int.class) clazz = Integer.class;
        if (clazz == long.class) clazz = Long.class;
        if (clazz == float.class) clazz = Float.class;
        if (clazz == double.class) clazz = Double.class;

        if (clazz.isArray()) {
            Class<?> actualType = ArrayUtils.resolveActualComponentType(clazz);
            if (!actualType.isPrimitive()) return type;

            int dimension = ArrayUtils.dimensionOf(clazz);
            Class<?> wrappedType = wrap(actualType);

            return (Class<T>) ArrayUtils.resolveArrayType(wrappedType, dimension);
        }
        return (Class<T>) clazz;
    }

    public static <T> Class<T> unwrap(final Class<T> type) {
        if (type == null) {
            return null;
        }

        Class<?> clazz = type;
        if (clazz == Void.class) clazz = void.class;
        if (clazz == Boolean.class) clazz = boolean.class;
        if (clazz == Byte.class) clazz = byte.class;
        if (clazz == Short.class) clazz = short.class;
        if (clazz == Character.class) clazz = char.class;
        if (clazz == Integer.class) clazz = int.class;
        if (clazz == Long.class) clazz = long.class;
        if (clazz == Float.class) clazz = float.class;
        if (clazz == Double.class) clazz = double.class;

        if (clazz.isArray()) {
            Class<?> actualType = ArrayUtils.resolveActualComponentType(clazz);
            if (actualType.isPrimitive()) {
                return type;
            }

            int dimension = ArrayUtils.dimensionOf(clazz);
            Class<?> unwrappedType = unwrap(actualType);

            return (Class<T>) ArrayUtils.resolveArrayType(unwrappedType, dimension);
        }

        return (Class<T>) clazz;
    }

    public static Set<Class<?>> getAllExtendedOrImplementedTypesAsSet(Class<?> clazz) {
        if (clazz == null) {
            return Collections.emptySet();
        }

        List<Class<?>> classes = new ArrayList<>();

        do {
            classes.add(clazz);

            // First, adds all the interfaces implemented by this class.
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                classes.addAll(Arrays.asList(interfaces));

                for (Class<?> it : interfaces) {
                    classes.addAll(getAllExtendedOrImplementedTypesAsSet(it));
                }
            }

            // Adds the super class.
            Class<?> superclass = clazz.getSuperclass();

            // All interfaces don't have java.lang.Object as superclass.
            // They return null, so breaks the recursive cycle and returns.
            if (superclass == null) {
                break;
            }

            // Now inspects the superclass.
            clazz = superclass;
        } while (clazz != Object.class);

        return new LinkedHashSet<>(classes);
    }

    public static List<Class<?>> resolveActualTypes(final Type type) {
        // When type is concrete type: java.lang.String
        if (type instanceof Class<?>) {
            return Collections.singletonList((Class<?>) type);
        }

        // When type is wildcard type:
        // java.util.List<? super java.lang.String>
        // java.util.List<? extends java.lang.String>
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type t = ArrayUtils.exists(lowerBounds) ? lowerBounds[0] : wildcardType.getUpperBounds()[0];

            if (t instanceof Class<?>) {
                return Collections.singletonList((Class<?>) t);
            }

            // GenericArrayType: T[]
            if (t instanceof GenericArrayType) {
                return Collections.singletonList(Object[].class);
            }

            // TypeVariable: T
            return Collections.emptyList();
        }

        // GenericArrayType: T[]
        if (type instanceof GenericArrayType) {
            return Collections.singletonList(Object[].class);
        }

        // null, TypeVariable: T
        if (!(type instanceof ParameterizedType)) {
            return Collections.emptyList();
        }

        List<Class<?>> types = new ArrayList<>();
        ParameterizedType paramType = (ParameterizedType) type;
        for (Type t : paramType.getActualTypeArguments()) {
            List<Class<?>> list = resolveActualTypes(t);
            if (!list.isEmpty()) {
                types.addAll(list);
            }
        }

        return types;
    }

}
