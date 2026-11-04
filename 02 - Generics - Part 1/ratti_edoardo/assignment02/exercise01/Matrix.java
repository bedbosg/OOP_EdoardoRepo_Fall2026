package assignment02.exercise01;



/*
Develop a program that implements a generic Matrix class, which uses a two-dimensional array to store the
matrix elements. The constructor of the Matrix class should take the number of rows and columns as
parameters to set up the internal array. Ensure your program includes the following methods:
1. set(row, col, value): stores the given value at the specified row and column.
2. get(row, col): retrieves and returns the value at the specified row and column.
3. print(): prints the entire matrix, using the toString() method of each element.
4. transpose(): returns a new Matrix instance that represents the transpose of the current matrix.
To verify your implementation, create at least two Matrix instances with different data types (such as Integer
and String), and thoroughly test all the methods.
 */

public class Matrix <T> {
    private T[][] matrix;
    public void set(int row, int col, T value){
        if(row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length){
            matrix[row][col] = value;
        }
    }

    public T get(int row, int col){
        if(row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length) {
            return matrix[row][col];
        }else{
            return null;
        }
    }
    public void print(){
        for(T[] row : matrix){
            for (T element : row){
                if(element != null) {
                    System.out.print(element.toString() + " ");
                }
            }
            System.out.println();
        }
    }
    public Matrix<T> transpose(){
        int cols = matrix.length;
        int rows = matrix[0].length;
        Matrix<T> transposed = new Matrix<>(rows, cols);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed.set(j, i, get(i, j));
            }
        }
        return transposed;
    }

    public Matrix(int rows, int cols){
        if(rows >= 0 && cols >= 0){
            matrix = (T[][])(new Object[rows][cols]);
        }else{
            matrix = null;
        }
    }
}
class testMatrix{
    public static void main(String[] args) {
        System.out.println("=== TEST 1: MATRICE INTEGER (2x3) ===");
        Matrix<Integer> intMatrix = new Matrix<>(2, 3);

        int val = 1;
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 3; c++) {
                intMatrix.set(r, c, val++);
            }
        }

        System.out.println("Matrice Integer Originale (2x3):");
        intMatrix.print();

        System.out.println("\nValore estratto con get(0, 2): " + intMatrix.get(0, 2));

        System.out.println("\nMatrice Integer Trasposta (3x2):");
        Matrix<Integer> intTransposed = intMatrix.transpose();
        intTransposed.print();

        System.out.println("\n==========================================");
        System.out.println("=== TEST 2: MATRICE STRING (3x2) ===");
        Matrix<String> strMatrix = new Matrix<>(3, 2);

        strMatrix.set(0, 0, "A1");
        strMatrix.set(0, 1, "B1");
        strMatrix.set(1, 0, "A2");
        strMatrix.set(1, 1, "B2");
        strMatrix.set(2, 0, "A3");
        strMatrix.set(2, 1, "B3");

        System.out.println("Matrice String Originale (3x2):");
        strMatrix.print();

        System.out.println("\nValore estratto con get(1, 1): " + strMatrix.get(1, 1));

        System.out.println("\nMatrice String Trasposta (2x3):");
        Matrix<String> strTransposed = strMatrix.transpose();
        strTransposed.print();
    }
}

