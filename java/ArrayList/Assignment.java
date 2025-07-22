import java.util.*;

public class Assignment {

    // Method to check if list is monotonic (increasing or decreasing)
    public static boolean getMono(ArrayList<Integer> list) {
        boolean inc = true, dec = true;

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                inc = false;
            }
            if (list.get(i) < list.get(i + 1)) {
                dec = false;
            }
        }

        return inc || dec;
    }

    // Method to get lonely numbers (no neighbors with ±1)
    public static ArrayList<Integer> getLonely(ArrayList<Integer> list) {
        Collections.sort(list);
        ArrayList<Integer> nums = new ArrayList<>();

        // Handle single element
        if (list.size() == 1) {
            nums.add(list.get(0));
            return nums;
        }

        // Check first element
        if (list.get(0) + 1 < list.get(1)) {
            nums.add(list.get(0));
        }

        // Check middle elements
        for (int i = 1; i < list.size() - 1; i++) {
            if ((list.get(i - 1) + 1 < list.get(i)) && (list.get(i) + 1 < list.get(i + 1))) {
                nums.add(list.get(i));
            }
        }

        // Check last element
        if (list.get(list.size() - 2) + 1 < list.get(list.size() - 1)) {
            nums.add(list.get(list.size() - 1));
        }

        return nums;
    }

    public static void main(String[] args) {
        // Test getMono
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 2));
        System.out.println("Is monotonic: " + getMono(list));

        // Test getLonely
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(10, 6, 5, 8));
        ArrayList<Integer> result = getLonely(list1);

        System.out.print("Lonely numbers: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}
