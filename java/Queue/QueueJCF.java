import java.util.*;
import java.util.LinkedList;
public class QueueJCF {
    public static void main(String[] args) {
       //  Queue q=new Queue();
       ///We cannnot create object of queue bcoz it is interface it is implemented using LL and Array deque
       Queue <Integer> q = new LinkedList<>(); //ArrayDeque<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        while(!q.isEmpty())
        {
            System.out.println(q.peek());
            q.remove();
        }
    }
}

    

