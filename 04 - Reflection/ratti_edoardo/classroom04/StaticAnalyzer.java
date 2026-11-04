package classroom04;

import java.lang.reflect.*;

public class StaticAnalyzer {
    public static void main(String[] args) {
        analyze("classroom04.ServerConfiguration");
    }

    private static void analyze(String className) {
        System.out.println("Analyzing " + className);
        System.out.println();
        // TODO to implement
        try {
            Class<?> clazz = Class.forName(className);
            System.out.printf("public class %s {%n", className);
            Field[] fields = clazz.getDeclaredFields();
            Method[] methods = clazz.getDeclaredMethods();
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                //fields
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                Object fieldValue = field.get(instance);
                if(!Modifier.isPrivate(field.getModifiers())){
                    System.out.printf("[ERROR] field is not private");
                }
                //Getters
                String getterName = "get" + capitalize(fieldName);
                String setterName = "set" + capitalize(fieldName);
                if(!methodExists(clazz, getterName)){
                    System.out.println("[ERROR] getter not found: " + getterName);
                }
                //Setters
                if(!methodExists(clazz, setterName)){
                    System.out.println("[ERROR] setter not found: " + setterName);
                }
            }
            System.out.printf("}%n");

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (NoSuchMethodException e) {
            System.out.println("No such method");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to create instance");
        }
    }

    public static boolean methodExists(Class<?> clazz, String methodName){
        try {
            clazz.getDeclaredMethod("methodName");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}