package io.github.teamdevintia.magicpotions.util;

import java.lang.reflect.Field;

/**
 * util to make reflection easier
 *
 * @author MiniDigger
 */
public final class ReflectionUtil {

    /**
     * Returns the value of a private field
     *
     * @param fieldName the field to access
     * @param clazz the class the field is in
     * @param object the instance of the class
     * @return the value of the field, null if an error occurred
     */
    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }
}
