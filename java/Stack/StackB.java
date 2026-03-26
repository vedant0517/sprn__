//import java.util.ArrayList;
import java.util.*;

public class StackB {

    // ========== STACK IMPLEMENTATION USING ARRAYLIST ==========
    // Time Complexity: Push O(1), Pop O(1), Peek O(1)
    // Space Complexity: O(n)
    // Advantages: Direct index access, cache-friendly
    // Disadvantages: May waste space if capacity exceeds size
    /*
    static class Stack{
        static ArrayList<Integer> list=new ArrayList<>();
        
        public static boolean isEmpty(){
            return list.size()==0;
        }
        
        // push: add element to top (end of list)
        public static void push(int data){
            list.add(data);
        }
        
        // pop: remove element from top (end of list)
        public static int pop(){
            if(isEmpty()){
                return -1;
            }
            int top=list.get(list.size()-1);
            list.remove(list.size()-1);
            return top;
        }
        
        // peek: view element at top without removing
        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return list.get(list.size()-1);
        }
    }
    
    // Usage Example:
    // Stack s = new Stack();
    // s.push(10); s.push(20); s.push(30);
    // System.out.println(s.peek()); // Output: 30
    // System.out.println(s.pop());  // Output: 30
    */

    // ========== STACK IMPLEMENTATION USING LINKEDLIST (Custom Node) ==========
    // Time Complexity: Push O(1), Pop O(1), Peek O(1)
    // Space Complexity: O(n)
    // Advantages: Dynamic memory allocation, no wasted space
    // Disadvantages: Extra memory for pointers, no direct access
    /*
    static class Node{
        int data;
        Node next;
        
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    
    static class Stack{
        static Node head=null;

        public static boolean isEmpty(){
            return head==null;
        }
        
        // push: add element to top (at head)
        public static void push(int data){
            Node newNode=new Node(data);
            if(isEmpty()){
                head=newNode;
                return;
            }
            newNode.next=head;
            head=newNode;
        }
        
        // pop: remove element from top (remove head)
        public static int pop(){
            if(isEmpty()){
                return -1;
            }
            int top=head.data;
            head=head.next;
            return top;
        }
        
        // peek: view element at top without removing
        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return head.data;
        }
    }
    
    // Usage Example:
    // Stack s = new Stack();
    // s.push(10); s.push(20); s.push(30);
    // System.out.println(s.peek()); // Output: 30
    // System.out.println(s.pop());  // Output: 30
    */
    public static void main(String[] args) {
      //  Stack s=new Stack();
      Stack<Integer> s=new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);

        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();
        }
    }

}
