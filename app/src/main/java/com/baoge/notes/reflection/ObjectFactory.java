package com.baoge.notes.reflection;

public class ObjectFactory {

    public static Object getInstance2(String name) {
        try {
            Class<?> cls = Class.forName(name);
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T getInstance(Class<T> cls) {

        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
