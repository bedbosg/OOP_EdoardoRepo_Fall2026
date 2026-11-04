package assignments03.exercise01;

public class MaximumFinder {
    public static <T extends Comparable<T>> T findMax(T[] array){
        T max = array[0];
        for(T e : array){
            if(e.compareTo(max) > 0){
                max = e;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 0, 87, 64, 999, 1000};
        String[] strings = {"Ciao", "Alpha", "Beta", "Gamma","alliance"};
        Double[] doubles = {1.3, 13.0, 17.21, 0.99999999, 19.0000001};
        System.out.println(findMax(integers));
        System.out.println(findMax(strings));
        System.out.println(findMax(doubles));

    }
}


