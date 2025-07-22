import java.util.*;

public class PairSUm {

    // Two-pointer approach (requires sorted list)
    public static boolean PairSum1(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    // Brute-force approach
    public static boolean BruteForce(ArrayList<Integer> list, int target) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Two-pointer approach (roated and rotated)
    public static boolean PairSum2(ArrayList<Integer> list, int target) {
        int pivot=-1;
        for(int i=0;i<list.size();i++){
            if(list.get(i)>list.get(i+1)){
                pivot=i;
                break;
            }
        }
        int left=pivot+1;//smallest
        int right=pivot;
        while(left!=right){
            int sum=list.get(left)+list.get(right);
            //case 1 
            if(sum==target){
                return true;
            }
            //case 2
            if(sum<target){
                left=(left+1)%list.size();
            }else{
                right=(list.size()+right-1)%list.size();
            }
        }
        return false;

    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
         ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(11,15,6,7,8,9,10));

        System.out.println("PairSum1 (Two Pointer): " + PairSum1(list, 9));  // true
        System.out.println("BruteForce (Brute Force): " + BruteForce(list, 9)); // true

        System.out.println("PairSum1 (Two Pointer): " + PairSum1(list, 15));  // false
        System.out.println("BruteForce (Brute Force): " + BruteForce(list, 15)); // false

        System.out.println(" (Pair 2): " + PairSum2(list2, 16));
    }
}
