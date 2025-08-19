import java.util.*;

public class conditions {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int age=sc.nextInt();
        //If else
    
        if(age>18){
           System.out.println("Congrats Eligible fucker");}
           else 
           {
            System.out.println("fucked up");
           }
         if (age % 2== 0){
            System.out.println("Even");
         }else{System.out.println("odd");}
         sc.close();
    }
    
}
