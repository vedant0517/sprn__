import java.util.LinkedList;//JCF
public class Practice {
    //create
   public static void main(String[] args) {
    //create
     LinkedList<Integer> ll=new LinkedList<>();

     //add
     ll.addLast(10);
     ll.addLast(4);
     ll.addFirst(2);

     System.out.println(ll);

     ll.removeLast();
     ll.removeFirst();
     System.out.println(ll);
   }

    
}
