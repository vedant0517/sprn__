
//0(n)   ask in interview mostly
import java.util.LinkedList;

import java.util.*;
public class interleave2halves {

    public static void interleave(Queue<Integer> q)
    {
        Queue<Integer> firstHalf=new LinkedList<>();
        int size=q.size(); //define spearate size coz it will will vary if usr methods
        for(int i=0;i<size/2;i++)
        {
            firstHalf.add(q.remove());
        }

        while(!firstHalf.isEmpty())
        {
            q.add(firstHalf.remove()); //for insert 1st half
            q.add(q.remove());  //for insert 2nd half
        }

    }

    public static void main(String[] args) {
        Queue<Integer> q=new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);   
        q.add(7);
        q.add(8);   
        q.add(9);
        q.add(10);
        
        interleave(q);

        //print
        while (!q.isEmpty())
        {
            System.out.print(q.remove()+" ");
            
        }
       
    }
    
}
