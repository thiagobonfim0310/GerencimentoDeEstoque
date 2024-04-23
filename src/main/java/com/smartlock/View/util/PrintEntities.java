package com.smartlock.View.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

/**
 * printEntities
 */
public class PrintEntities {
    /*
     * 
     * public void printClass(Object object) {
     * 
     * Class<?> entity = object.getClass();
     * 
     * Field[] attributes = entity.getDeclaredFields();
     * 
     * for (Field attibute : attributes) {
     * attibute.setAccessible(true);
     * try {
     * System.out.println(attibute.getName() + ": " + attibute.get(object));
     * } catch (IllegalAccessException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     */
    public void printClass(Object object) {
        printClass(object, 0);
    }

    private void printClass(Object object, int depth) {
        Class<?> entity = object.getClass();
        Field[] attributes = entity.getDeclaredFields();

        for (Field attribute : attributes) {
            attribute.setAccessible(true);
            try {
                Object value = attribute.get(object);
                if (value != null) {
                    if (depth > 0) {
                        System.out.print("    ".repeat(depth));
                        System.out.print("└─ ");
                    }
                    if (attribute.getType().equals(UUID.class)) {
                        System.out.println(attribute.getName() + ": " + ((UUID) value).toString());
                    } else if (value instanceof Collection) {
                        // Se for uma coleção, imprime cada elemento
                        System.out.println(attribute.getName() + ": ");
                        printCollection((Collection<?>) value, depth + 1);
                    } else if (!attribute.getType().isPrimitive() && !attribute.getType().equals(String.class)) {
                        System.out.println(attribute.getName() + ": ");
                        printClass(value, depth + 1);
                    } else {
                        System.out.println(attribute.getName() + ": " + value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void printCollection(Collection<?> collection, int depth) {
        for (Object element : collection) {
            if (depth > 0) {
                System.out.print("    ".repeat(depth));
                System.out.print("└─ ");
            }
            if (element instanceof UUID) {
                System.out.println(element.toString());
            } else {
                printClass(element, depth + 1);
            }
        }
    }
}