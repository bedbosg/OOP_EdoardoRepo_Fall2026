package assignment04.exercise01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class Coordinate {
    private float lat;
    private float lon;

    public Coordinate() {
        this.lat = (float) (Math.random() * 180. - 90.); // between -90;+90
        this.lon = (float) (Math.random() * 360. - 180.);// between -180;+180
    }

    public Coordinate(float lat, float lon) {
        if (!isValidLat(lat)) {
            throw new IllegalArgumentException("Invalid latitude");
        }
        if (!isValidLon(lat)) {
            throw new IllegalArgumentException("Invalid longitude");
        }
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    private static boolean isValidLat(float lat) {
        return (lat <= 90 && lat >= -90);
    }

    private static boolean isValidLon(float lon) {
        return (lon <= 180 && lon >= -180);
    }

    public double distance(Coordinate other) {
        return Math.acos(Math.sin(lat) * Math.sin(other.lat) + Math.cos(lat) * Math.cos(other.lat) * Math.cos(other.lon - lon)) * 6371;
    }

    @Override
    public String toString() {
        return String.format("Coordinate@%s (lat=%.5f, lon=%.5f)", hashCode(), getLat(), getLon());
    }
}

public class DocumentationHelper {

    private static void toMarkdown(final Class<?> theClass) {
        String className = theClass.getName();
        System.out.printf("# `%s`%n%n", className);
        // Constructors
        System.out.printf("## Constructor(s)%n%n");
        for (Constructor<?> declaredConstructor : theClass.getDeclaredConstructors()) {
            System.out.printf("### `%s(", declaredConstructor.getDeclaringClass().getSimpleName());
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            StringBuilder parametersDescribe = new StringBuilder();
            for (int i = 0; i < parameterTypes.length; i++) {
                parametersDescribe.append("- `").append(parameterTypes[i]).append(" arg").append(i)
                        .append("`: TODO describe parameter.").append(System.lineSeparator());

                System.out.printf("%s", parameterTypes[i]);
                if(i != parameterTypes.length-1){
                    System.out.print(", ");
                }
            }
            System.out.printf(")`%n");
            System.out.printf("%s%n", parametersDescribe);
            System.out.printf("TODO describe constructor%n%n");
        }
        // Methods
        System.out.printf("## Method(s)%n%n");
        for (Method declaredMethod : theClass.getDeclaredMethods()) {
            System.out.printf("### `%s %s(",declaredMethod.getReturnType().getCanonicalName(), declaredMethod.getName());
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            StringBuilder parametersDescribe = new StringBuilder();
            for (int i = 0; i < parameterTypes.length; i++) {
                parametersDescribe.append("- `").append(parameterTypes[i].getSimpleName()).append(" arg").append(i)
                        .append("`: TODO describe parameter.").append(System.lineSeparator());
                System.out.printf("%s", parameterTypes[i].getSimpleName());
                if(i != parameterTypes.length-1){
                    System.out.printf(", ");
                }
            }
            System.out.printf(")`%n");
            System.out.printf("%s%n", parametersDescribe);
            System.out.printf("TODO describe method%n%n");
        }


    }

    public static void main(String[] args) {
        toMarkdown(Coordinate.class);
    }
}
