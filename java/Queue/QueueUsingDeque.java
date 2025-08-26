import java.util.Deque;
import java.util.LinkedList;

public class QueueUsingDeque {
    static class MyQueue {
        private Deque<Integer> dq = new LinkedList<>();

        // enqueue (O(1))
        public void enqueue(int x) {
            dq.addLast(x);
        }

        // dequeue (O(1))
        public int dequeue() {
            if (dq.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return dq.removeFirst();
        }

        // peek front (O(1))
        public int peek() {
            if (dq.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return dq.getFirst();
        }

        public boolean isEmpty() {
            return dq.isEmpty();
        }

        public int size() {
            return dq.size();
        }
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println("Front element (peek): " + q.peek());

        System.out.println("Dequeuing all elements:");
        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
