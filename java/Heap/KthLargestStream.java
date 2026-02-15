import java.util.*;

public class KthLargestStream {

    public static void kthLargest(int[] stream, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < stream.length; i++) {
            
            // Add element to heap
            minHeap.add(stream[i]);

            // If heap size exceeds k, remove smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }

            // Print kth largest
            if (minHeap.size() < k) {
                System.out.print("_ ");
            } else {
                System.out.print(minHeap.peek() + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] stream = {10, 20, 11, 70, 50, 40, 100, 5};
        int k = 3;

        kthLargest(stream, k);
    }
}
