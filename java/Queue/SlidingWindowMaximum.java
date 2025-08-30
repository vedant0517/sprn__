import java.util.*;
public class SlidingWindowMaximum {

    // Brute Force Method
    static void printSlidingWindowMaximumBruteForce(int arr[], int N, int K) {
        if (arr == null || K <= 0 || K > N) {
            System.out.println();
            return;
        }
        for (int i = 0; i < N - K+1; i++) {
            int currMax = arr[i];
            for (int j = 1; j < K; j++) {
                if (arr[i + j] > currMax) {
                    currMax = arr[i + j];
                }
            }
            System.out.print(currMax + " ");
        }
        System.out.println();
    }

    // Optimized Method using Deque
    static void printSlidingWindowMaximumDeque(int arr[], int N, int K) {
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            // Remove out-of-window elements
            while (!dq.isEmpty() && dq.peekFirst() <= i - K) {
                dq.pollFirst();
            }

            // Remove smaller elements from back
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                 dq.pollLast();
            }

            dq.addLast(i);

            // Print maximum when window is complete
            if (i >= K-1 ) {
                System.out.print(arr[dq.peekFirst()] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int N = arr.length;
        int K = 3;

        System.out.println("Brute Force Output:");
        printSlidingWindowMaximumBruteForce(arr, N, K);

        System.out.println("Deque Optimized Output:");
        printSlidingWindowMaximumDeque(arr, N, K);
    }
}











// import java.util.*;

// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         Deque<Integer> dq = new LinkedList<>(); // stores indices
//         List<Integer> res = new ArrayList<>();

//         for (int i = 0; i < nums.length; i++) {
//             // Remove out-of-window elements
//             while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
//                 dq.pollFirst();
//             }

//             // Remove smaller elements from back
//             while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
//                 dq.pollLast();
//             }

//             dq.addLast(i);

//             // Add to result after first window is complete
//             if (i >= k - 1) {
//                 res.add(nums[dq.peekFirst()]);
//             }
//         }

//         // Convert List<Integer> to int[]
//         int[] ans = new int[res.size()];
//         for (int i = 0; i < res.size(); i++) {
//             ans[i] = res.get(i);
//         }

//         return ans;
//     }
// }




// import java.util.*;

// class Solution {
//     public List<Integer> maxSlidingWindow(int[] nums, int k) {
//         // Initialize deque to store indices of elements in the window
//         Deque<Integer> dq = new LinkedList<>();
//         List<Integer> res = new ArrayList<>();

//         // Process the first window
//         for (int i = 0; i < k; i++) {
//             // Remove elements that are less than the current element
//             while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
//                 dq.pollLast();
//             }
//             dq.offerLast(i);
//         }

//         // Add the maximum of the first window to the result
//         res.add(nums[dq.peekFirst()]);

//         // Process the remaining windows
//         for (int i = k; i < nums.length; i++) {
//             // Remove elements not in the current window
//             while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
//                 dq.pollFirst();
//             }

//             // Remove elements smaller than the current element
//             while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
//                 dq.pollLast();
//             }

//             // Add the current element's index to the deque
//             dq.offerLast(i);

//             // Add the maximum of the current window to the result
//             res.add(nums[dq.peekFirst()]);
//         }

//         return res;
//     }
// }