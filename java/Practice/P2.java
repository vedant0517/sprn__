package Practice;
import java.util.*;

public class P2 {
    public static int getGreater(int a, int b){
        if(a>b)
        {
            return a;
        }
        else
        {
        return b;
        }
          
    } 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        getGreater(a, b);
        System.out.println("The Greater number is:" +getGreater(a, b));
        sc.close();
    }
}


