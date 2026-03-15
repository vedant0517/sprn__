import java.util.*;

class sumSmaller {

    public static void main(String[] args) {
        int nums[]={1,2,3,4,5,6,7,8,9};
        int tgt=15;
        System.out.println(Smaller(nums,tgt));
    }
    public static int Smaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }
}