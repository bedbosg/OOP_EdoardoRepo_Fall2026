package asynchronous02;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtilities {
    public static <T> void  printArray(T[] array){
        for (T element : array){
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T> T getFirst(T[] array){
        if (array == null || array.length == 0){
            return  null;
        }

        return array[0];
    }

    public static<T> boolean swap(T[] array, int i, int j){
        if (array == null || array.length == 0) {
            return false;
        }
        if (i < 0 || i >= array.length || j < 0 || j >= array.length) {
            return false;
        }
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

        return true;
    }

    public static<T> long countOccurrences(T[] array, T element){
        if (array == null || array.length == 0) {
            return 0L;
        }
        long count = 0L;

        for (T e : array){
            if (e.equals(element)){
                count++;
            }
        }

        return count;
    }

    public static <T> List<T> toList(T[] array){
        List<T> list = new ArrayList<>();
        for(T element : array){
            list.add(element);
        }
        return list;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        String[] stringArray = {"Hello", "World"};

        ArrayUtilities.printArray(intArray);
        ArrayUtilities.printArray(doubleArray);
        ArrayUtilities.printArray(stringArray);

        System.out.printf("First element of intArray: %s%n", ArrayUtilities.getFirst(intArray));
        System.out.printf("First element of doubleArray: %s%n", ArrayUtilities.getFirst(doubleArray));
        System.out.printf("First element of stringArray: %s%n", ArrayUtilities.getFirst(stringArray));

        System.out.println("Swapping intArray, doubleArray and stringArray");
        ArrayUtilities.swap(intArray, 0, 1);
        ArrayUtilities.swap(doubleArray, 0, 1);
        ArrayUtilities.swap(stringArray, 0, 1);

        ArrayUtilities.printArray(intArray);
        ArrayUtilities.printArray(doubleArray);
        ArrayUtilities.printArray(stringArray);

        System.out.printf("Count occurrences of 1 in intArray: %d%n", ArrayUtilities.countOccurrences(intArray, 1));
        System.out.printf("Count occurrences of 1.1 in doubleArray: %d%n", ArrayUtilities.countOccurrences(doubleArray, 1.1));
        System.out.printf("Count occurrences of Hello in stringArray: %d%n", ArrayUtilities.countOccurrences(stringArray, "Hello"));

        // toList
        ArrayUtilities.toList(intArray).forEach(System.out::println);
        ArrayUtilities.toList(doubleArray).forEach(System.out::println);
        ArrayUtilities.toList(stringArray).forEach(System.out::println);
    }
}
