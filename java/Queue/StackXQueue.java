import java.util.*;
import java.util.LinkedList;

public class StackXQueue {
    static class Stack{
        static Queue <Integer> q1 =new LinkedList<>();
        static Queue <Integer> q2 =new LinkedList<>();

        public static boolean isEmpty(){
            return q1.isEmpty() && q2.isEmpty();
        }

        //push -- 0(1)
        public static void push(int data){
            if(!q1.isEmpty()){
                q1.add(data);
            }else{
                q2.add(data);
            }
            
        }

        //pop -- 0(1)
        public static int pop(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int top=-1;
            //case 1
           if(!q1.isEmpty()){
            while(!q1.isEmpty()){
                top=q1.remove();
                if(q1.isEmpty()){
                    break;
                }
                q2.add(top);
            }
        }else{
            //case 2
            while(!q2.isEmpty()){
                top=q2.remove();
                if(q2.isEmpty()){
                    break;
                }
                q1.add(top);
             }
           }
           return top;
        }

        //peak
        public static int peak(){
               if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int top=-1;
            //case 1
           if(!q1.isEmpty()){
            while(!q1.isEmpty()){
                top=q1.remove();
                q2.add(top);
            }
        }else{
            //case 2
            while(!q2.isEmpty()){
                top=q2.remove();
                q1.add(top);
             }
           }
           return top;
        }
    }
    public static void main(String[] args) {
        Stack q=new Stack();
        q.push(1);
        q.push(2);
        q.push(3);

        while(!q.isEmpty()){
            System.out.println(q.peak());
            q.pop();
        }
    }
}


// import java.util.*;

// public class StackXQueue {
//     static class Stack {
//         Queue<Integer> q1 = new LinkedList<>();
//         Queue<Integer> q2 = new LinkedList<>();

//         public boolean isEmpty() {
//             return q1.isEmpty();
//         }

//         // push -- O(n)
//         public void push(int data) {
//             q2.add(data);
//             // move all elements from q1 to q2
//             while (!q1.isEmpty()) {
//                 q2.add(q1.remove());
//             }
//             // swap q1 and q2
//             Queue<Integer> temp = q1;
//             q1 = q2;
//             q2 = temp;
//         }

//         // pop -- O(1)
//         public int pop() {
//             if (isEmpty()) {
//                 System.out.println("Stack is empty");
//                 return -1;
//             }
//             return q1.remove();
//         }

//         // peek -- O(1)
//         public int peek() {
//             if (isEmpty()) {
//                 System.out.println("Stack is empty");
//                 return -1;
//             }
//             return q1.peek();
//         }
//     }

//     public static void main(String[] args) {
//         Stack st = new Stack();
//         st.push(1);
//         st.push(2);
//         st.push(3);

//         while (!st.isEmpty()) {
//             System.out.println("Top = " + st.peek());
//             st.pop();
//         }
//     }
// }
