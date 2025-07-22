import java.util.ArrayList;

public class MultiDimensionalArrayList {
    public static void main(String[] args) {
        // Create the outer ArrayList (like rows of a matrix)
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        // Adding rows to the matrix
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                row.add(i+j); // Sample value: sum of row index and column index
            }
            matrix.add(row);
        }

        // Printing the 2D ArrayList
        System.out.println("2D ArrayList (Matrix):");
        for (ArrayList<Integer> row : matrix) {
            System.out.println(row);
        }

        // Accessing a specific element (example: element at row 1, column 2)
        int value = matrix.get(1).get(2);
        System.out.println("Element at row 1, column 2: " + value);


        //Printing
        for (int i = 0; i<matrix.size(); i++) {
            ArrayList<Integer> currList = matrix.get(i);
            for (int j = 0; j<currList.size(); j++) {
                System.out.print(currList.get(j)+" ");
            }
            System.out.println();
        }
    }

}
