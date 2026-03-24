public class Main {

    // 🔵 Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    // 🟢 Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    
    // 🟡 Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i]; 
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // 🔁 Print Array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] original = {5, 3, 2, 4, 1};

        int[] bubbleArr = original.clone();
        int[] selectionArr = original.clone();
        int[] insertionArr = original.clone();

        System.out.print("Original Array: ");
        printArray(original);

        bubbleSort(bubbleArr);
        System.out.print("After Bubble Sort: ");
        printArray(bubbleArr);

        selectionSort(selectionArr);
        System.out.print("After Selection Sort: ");
        printArray(selectionArr);

        insertionSort(insertionArr);
        System.out.print("After Insertion Sort: ");
        printArray(insertionArr);
    }
}