import java.util.*;
public class Operations{
public static void main(String[] args) {
    //ClassName<> objectname= new ClassName<>();
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);list.add(2);list.add(3);//Add Element 0(1)
    System.out.println(list);
    
    int element =list.get(2);//Get Element 0(1)
    System.out.println(element);

    list.remove(2);//Remove element 0(n)
    System.out.println(list);

    list.set(1, 5);//Set Elemnt at Index 0(n)
    System.out.println(list);

    System.out.println( list.contains(5)); //contains check if element is present or not
   
    //size method
    System.out.println(list.size());

    //print ArrayLIst
    for(int i=0;i<list.size();i++){
        System.out.print(list.get(i)+ " ");
    }





    
}
}