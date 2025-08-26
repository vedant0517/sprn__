import java.util.Deque;
import java.util.LinkedList;

public class StackUsingDeque {
    static class Stack {
    private Deque<Integer> dq = new LinkedList<>();

        // push onto stack (O(1))
        public void push(int x) {
            dq.addLast(x); 
        }

        // pop from stack (O(1))
        public int pop() {
            if (dq.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return dq.removeLast();
        }

        // peek top element (O(1))
        public int peek() {
            if (dq.isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return dq.getLast();
        }

        public boolean isEmpty() {
            return dq.isEmpty();
        }

        public int size() {
            return dq.size();
        }
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println("Top element (peek): " + st.peek());

        System.out.println("Popping all elements:");
        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }
}
