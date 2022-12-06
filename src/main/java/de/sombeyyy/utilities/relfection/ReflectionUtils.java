package de.sombeyyy.utilities.relfection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ReflectionUtils {

    private ReflectionUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static List<Field> getInheritedFields(final Class<?> type) {
        if(type == null) throw new IllegalArgumentException("Class cannot be null");

        List<Field> fields = new ArrayList<>();
        for(Class<?> clazz = type; clazz != null; clazz = clazz.getSuperclass()) {
            fields.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
        }

        Predicate<Field> filter = it -> Modifier.isStatic(it.getModifiers());
        filter = filter.or(Field::isSynthetic);

        fields.remove(filter);
        return fields;
    }

    public static Object getFieldValue(final Object instance, final Field field) {
        if(field == null) throw new IllegalStateException("Field cannot be null");
        if(!Modifier.isStatic(field.getModifiers())) {
            if(instance == null) throw new IllegalStateException("Instance cannot be null");
        }

        boolean accessible = field.isAccessible();
        if(!accessible) field.setAccessible(true);

        try {
            return field.get(instance);
        } catch (IllegalAccessException exception) {
            String message = String.format("Failed to get value from the field(%s) of the class(%s)", field.getName(), field.getDeclaringClass().getName());
            throw new RuntimeException(message, exception);
        } finally {
            if(!accessible) field.setAccessible(false);
        }
    }

    //TODO: Implement more methods

}
