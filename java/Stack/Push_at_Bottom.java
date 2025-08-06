import java.util.*;
public class Push_at_Bottom {
    public static void pushAtBottom(Stack<Integer>s,int data){
        if(s.isEmpty()){
            s.push(data);
            return;
         }
        
        int top=s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }
    //reverse Stack
    public static void reverseStack(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int top=s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }

    public static void printStack(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }

    public static void main(String args[]){
        Stack<Integer> s=new Stack<>();
        s.push(3);
        s.push(2);
        s.push(1);
        //1 2 3
        // pushAtBottom(s, 4);
        // while(!s.isEmpty()){
        //     System.out.println(s.pop());
        // }

        reverseStack(s);
        printStack(s);
        //3 2 1
    }
}
