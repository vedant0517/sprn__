import java.util.*;

public class QueueXStack {
    static class Queue{
        static Stack<Integer> s1 =new Stack<>();
        static Stack<Integer> s2 =new Stack<>();

        public static boolean isEmpty(){
            return s1.isEmpty();
        }

        //add -- 0(n)
        public static void add(int data){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        //remove -- 0(1)
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int front=s1.pop();
            return front;
        }

        //peak
        public static int peak(){
             if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.peek();
        }

    }
    public static void main(String[] args) {
        Queue q=new Queue();
        q.add(1);
        q.add(2);
        q.add(3);

        while(!q.isEmpty()){
            System.out.println(q.remove());
        }
    }
}

//Dequeue  remove--0(n)
// import java.util.*;

// public class QueueXStack {
//     static class Queue {
//         static Stack<Integer> s1 = new Stack<>();
//         static Stack<Integer> s2 = new Stack<>();

//         public static boolean isEmpty() {
//             return s1.isEmpty();
//         }

//         // add -- O(1)
//         public static void add(int data) {
//             s1.push(data);
//         }

//         // remove -- O(n)
//         public static int remove() {
//             if (isEmpty()) {
//                 System.out.println("Queue is empty");
//                 return -1;
//             }

//             // Move elements to s2 except last one
//             while (s1.size() > 1) {
//                 s2.push(s1.pop());
//             }

//             int front = s1.pop(); // this is the oldest element

//             // Move elements back to s1
//             while (!s2.isEmpty()) {
//                 s1.push(s2.pop());
//             }

//             return front;
//         }

//         // peek -- O(n)
//         public static int peek() {
//             if (isEmpty()) {
//                 System.out.println("Queue is empty");
//                 return -1;
//             }

//             while (s1.size() > 1) {
//                 s2.push(s1.pop());
//             }

//             int front = s1.peek(); // front element

//             // Put it back
//             s2.push(s1.pop());
//             while (!s2.isEmpty()) {
//                 s1.push(s2.pop());
//             }

//             return front;
//         }
//     }

//     public static void main(String[] args) {
//         Queue q = new Queue();
//         q.add(1);
//         q.add(2);
//         q.add(3);

//         System.out.println(q.remove()); // 1
//         System.out.println(q.peek());   // 2
//         System.out.println(q.remove()); // 2
//         System.out.println(q.remove()); // 3
//     }
// }
