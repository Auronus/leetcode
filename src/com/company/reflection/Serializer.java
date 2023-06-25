package com.company.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Serializer {
    public static void main(String[] args) {
        SoEasyClass soEasyClass = buildObject();
        System.out.println(convertToJson(soEasyClass));
    }

    private static String convertToJson(Object object) {
        Map<String, Object> jsonElementsMap = new HashMap<>();
        try {
            Class cls = object.getClass();
            Field fields[] = cls.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(object);

                if (isPrimitiveClass(field.getType())) {
                    fieldValue = convertToJson(fieldValue);
                }
                jsonElementsMap.put(fieldName, fieldValue);
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
        return buildJsonFieldString(jsonElementsMap);
    }

    private static String buildJsonFieldString(Map<String, Object> jsonElementsMap) {
        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> {
                    if (entry.getValue() == null) {
                        return "\"" + entry.getKey() + "\":null";
                    }
                    if (entry.getValue().getClass().equals(String.class) || entry.getValue().getClass().isEnum()) {
                        return "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"";
                    }
                    return "\"" + entry.getKey() + "\":" + entry.getValue();
                })
                .collect(Collectors.joining(","));

        return "{" + jsonString + "}";
    }

    private static boolean isPrimitiveClass(Class<?> fieldClass) {
        return !fieldClass.isPrimitive() && !fieldClass.isArray() && !fieldClass.isEnum()
                && !fieldClass.equals(Byte.class) && !fieldClass.equals(Short.class)
                && !fieldClass.equals(Integer.class) && !fieldClass.equals(Long.class)
                && !fieldClass.equals(Float.class) && !fieldClass.equals(Double.class)
                && !fieldClass.equals(Boolean.class) && !fieldClass.equals(String.class)
                && !fieldClass.equals(Character.class);
    }

    private static SoEasyClass buildObject() {
        return new SoEasyClass(
                new SoEasySubClass(new Random().nextInt(), "SoEasySubClassStringValue"),
                new Random().nextDouble(),
                "SoEasyClassStringValue");
    }
}
