package assignment04.hierarchies;

import assignment04.hierarchies.vehicles.Car;
import assignment04.hierarchies.vehicles.F1Car;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        final String className = "assignment04.hierarchies.vehicles.F1Car";
        // FIXME implement tests to verify methods
        // Examples:
        // getPathToObject of F1Car --> F1Car, RacingCar, Car, Vehicle, Object
        // getPathToClass between F1Car and Vehicle --> Motorbike
        // getPathToClass between F1Car and String --> should return an empty list

        // getCommonAncestor between F1Car and Vehicle --> should return Vehicle
        // getCommonAncestor between F1Car and NascarCar --> should return RacingCar
        // getCommonAncestor between F1Car and Motorbike --> should return Vehicle
        // getCommonAncestor between F1Car and String --> should return Object

        System.out.println("\nInformation for Class: " + className);
        // countFields
        System.out.println("countFields: " + countFields(className));
        System.out.println("countFieldsHierarchy: " + countFieldsHierarchy(className));

        // field types
        System.out.println("getFieldTypes: " + getFieldTypes(className));
        System.out.println("getFieldTypesHierarchy: " + getFieldTypesHierarchy(className));

        // printMethodsByReturnType
        System.out.println("\nprintMethodsByReturnType(int)");
        // printMethodsByReturnType(className, ...);
        System.out.println("\nprintMethodsByReturnType(String)");
        //printMethodsByReturnType(className, ...);
        System.out.println("\nprintMethodsByReturnTypeHierarchy(int)");
        //printMethodsByReturnTypeHierarchy(className, ...);
        System.out.println("\nprintMethodsByReturnTypeHierarchy(String)");
        //printMethodsByReturnTypeHierarchy(className, ...);

        // Update car instance using reflection
        System.out.println("\nUpdating car instance using reflection");
        Car falconF1Car = new F1Car("Falcon", 2, "Alex Thunder", 120);
        System.out.println(falconF1Car);
        // update falconF1Car carNumber with 43
        // updateField(falconF1Car, "carNumber", 43);
        // update falconF1Car pilot name with: Max Lightning
        // updateField(falconF1Car, "pilotName", "Max Lightning");
        System.out.println(falconF1Car);
    }

    /**
     * Returns a list of strings representing all the classes in the class hierarchy from @param className to Object.
     */
    private static List<String> getPathToObject(String startClassName) {
        // FIXME to implement
        return Collections.emptyList();
    }

    /**
     * Returns a list of strings representing all the classes in the class hierarchy from startClassName to endClassName.
     * If there is no path between the two classes an empty list is returned.
     */
    private static List<String> getPathToClass(String startClassName, String endClassName) {
        // FIXME to implement
        return Collections.emptyList();
    }

    /**
     * Returns the fully qualified name of the common ancestor between {@param firstClassName} and {@param secondClassName}
     */
    private static String getCommonAncestor(String firstClassName, String secondClassName) {
        // FIXME to implement
        return "To implement!";
    }

    /**
     * Returns the number of fields declared in the specified class.
     */
    public static int countFields(String className) {
        // FIXME to implement
        return 0;
    }

    /**
     * Returns the total number of fields in the class hierarchy, starting from the given class.
     */
    public static int countFieldsHierarchy(String className) {
        // FIXME to implement
        return 0;
    }

    /**
     * Returns a set of field types declared in the specified class.
     */
    public static Set getFieldTypes(String className) {
        // FIXME to implement
        return Collections.emptySet();
    }

    /**
     * Returns a set of field types in the class hierarchy, starting from the specified class.
     */
    public static Set getFieldTypesHierarchy(String className) {
        // FIXME to implement
        return Collections.emptySet();
    }

    /**
     * Prints the methods of the specified class that return a given type.
     */
    public static void printMethodsByReturnType(String className /*, ... type*/) {
        // FIXME to implement
    }

    /**
     * Prints the methods of the specified class and its hierarchy that return a given type.
     */
    public static void printMethodsByReturnTypeHierarchy(String className/*, ... type*/) {
        // FIXME to implement
    }

    /**
     * Updates the value of a specified field in the given object instance using reflection.
     */
    public static void updateField(/*... instance ,*/ String fieldName /* , ... value*/) {
        // FIXME implement
    }
}
