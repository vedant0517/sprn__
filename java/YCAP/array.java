import java.util.*;

public class array {

    // 🔹 Insert at Beginning
    static int[] insertAtBeginning(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];

        newArr[0] = value;

        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }

        return newArr;
    }

    // 🔹 Insert at End
    static int[] insertAtEnd(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        newArr[arr.length] = value;

        return newArr;
    }

    // 🔹 Insert at Position
    static int[] insertAtPosition(int[] arr, int pos, int value) {
        int[] newArr = new int[arr.length + 1];

        for (int i = 0; i < pos; i++) {
            newArr[i] = arr[i];
        }

        newArr[pos] = value;

        for (int i = pos; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }

        return newArr;
    }

    // 🔹 Delete from Beginning
    static int[] deleteFromBeginning(int[] arr) {
        int[] newArr = new int[arr.length - 1];

        for (int i = 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }

        return newArr;
    }

    // 🔹 Delete from End
    static int[] deleteFromEnd(int[] arr) {
        int[] newArr = new int[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
        }

        return newArr;
    }

    // 🔹 Delete from Position
    static int[] deleteFromPosition(int[] arr, int pos) {
        int[] newArr = new int[arr.length - 1];

        for (int i = 0; i < pos; i++) {
            newArr[i] = arr[i];
        }

        for (int i = pos + 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }

        return newArr;
    }


    static void p(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(i==3 || i==0){
                    System.out.print("* ");
                }  
            }
            System.out.println();
        }
    }

    // 🔹 Traverse / Display
    static void display(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 🔹 Main Method
    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40};

        System.out.print("Original Array: ");
        display(arr);

        arr = insertAtBeginning(arr, 5);
        System.out.print("After Insert at Beginning: ");
        display(arr);

        arr = insertAtEnd(arr, 50);
        System.out.print("After Insert at End: ");
        display(arr);

        arr = insertAtPosition(arr, 2, 25);
        System.out.print("After Insert at Position: ");
        display(arr);

        arr = deleteFromBeginning(arr);
        System.out.print("After Delete from Beginning: ");
        display(arr);

        arr = deleteFromEnd(arr);
        System.out.print("After Delete from End: ");
        display(arr);

        arr = deleteFromPosition(arr, 2);
        System.out.print("After Delete from Position: ");
        display(arr);

        p();
    }
}