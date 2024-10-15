import java.util.*;
public class funcmul {
    public static int calculateMultiply(int a,int b){
        int multiply=a * b;
        return multiply;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        int multiply = calculateMultiply(a,b);
        System.out.println("the product is :"+multiply);
        sc.close();
    }
    
}
