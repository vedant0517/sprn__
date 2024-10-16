import java.util.*;
public class fact {
    public static void calculatetFact(int n){
       //loop
       if(n < 0){
        System.out.println("Invalid Number");
        return;
       }
       int factorial=1;
       for(int i=n; i>=1;i--) {
        factorial=factorial * i;
        
       }
       System.out.println("Factorial of given number is:" +factorial);
       return ;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        calculatetFact(n);
       
        sc.close(); 
        }
    
}
/*
 import java.util.*;
public class fact {
    public static int calculatetFact(int n){
       //loop
       int factorial=1;
       for(int i=n; i>=1;i--) {
        factorial=factorial * i;
        
       }
       
       return factorial;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
       int factorial= calculatetFact(n);
       System.out.println("Factorial of given number is:" +factorial);
        sc.close();
        }
    
}

 */