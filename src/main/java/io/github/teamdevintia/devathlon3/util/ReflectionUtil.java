package io.github.teamdevintia.devathlon3.util;

import java.lang.reflect.Field;

/**
 * Created by Martin on 16.07.2016.
 */
public class ReflectionUtil {

    /**
     * returns the value of a private field
     *
     * @param fieldName the field to access
     * @param clazz the class the field is in
     * @param object the instance of the class
     * @return the value of the vield, null if an error occured
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
