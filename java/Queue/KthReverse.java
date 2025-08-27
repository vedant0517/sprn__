import java.util.*;
import java.util.LinkedList;

/**
 * KthReverse
 *
 * Reverse the first k elements of a queue while leaving the remaining
 * elements in the same relative order. The method uses a stack to
 * temporarily hold the first k elements so they can be popped back
 * into the queue in reversed order.
 *
 * Contract:
 *  - Input: Queue<Integer> q, int k
 *  - Behavior: If k is invalid (<=0 or > q.size()) the queue is unchanged.
 *  - Result: First k elements in q are reversed; the remaining elements
 *            are rotated to preserve their original order relative to each other.
 *
 * Example:
 *  q = [1,2,3,4,5], k = 3  -> after reverse: [3,2,1,4,5]
 */
public class KthReverse {
    // Stack-based approach: O(n) time, O(k) extra space for stack
    public static void reverseStack(Queue<Integer> q, int k) {
        // size of queue before any modification
        int size = q.size();

        // Defensive checks: invalid k or empty queue -> nothing to do
        if (size < k || q.isEmpty() || k <= 0) return;

        // Use stack to reverse order of first k elements
        Stack<Integer> s = new Stack<>();

        // Step 1: dequeue first k elements from queue and push them onto stack
        // After this loop, stack's top is the k-th element and queue contains the rest
        for (int i = 0; i < k; i++) {
            s.push(q.remove());
        }

        // Step 2: pop all elements from stack and enqueue them back.
        // This places the first k elements in reversed order at the back of the queue.
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        // Step 3: The elements that were originally after the first k items
        // are currently at the front of the queue. We need to move them to the
        // back to restore their original relative order *after* the reversed block.
        // We perform (size - k) rotations: remove from front and add to back.
        for (int i = 0; i < size - k; i++) {
            q.add(q.remove());
        }
    }


    //Using Recursion
    static void KthReverseUsingRecursion(Queue<Integer> q, int k){
        helper(q,k);
        int sz=q.size()-k;
        while(sz-- > 0){
            int x=q.remove();
            q.add(x);
        }
    }

    static void helper(Queue<Integer> q, int k){
        if(k == 0)return;
        int e=q.remove();
        helper(q, k-1);
        q.add(e);
    }

    public static void main(String[] args) {
        // Demo queue
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);

        // Reverse first 2 elements: expected output -> 2 1 3 4

       //reverseStack(q, 2);
        KthReverseUsingRecursion(q, 2);

        // Print queue contents after operation
        while (!q.isEmpty()) {
            System.out.print(q.remove() + " ");
        }
    }

}
