import java.util.*;

//print sum
public class Functions {
    public static int calculateSum(int a, int b) {
      int sum= a + b;
      return sum;  
    }
//print product
    public static int calculateProduct(int a, int b)
    {
      int product=a * b;
      return  product;
    }
//print remainder
public static int calculateRemain(int a, int b)
{
  int remain=a % b;
  return  remain;
}
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt(); 
        int sum=calculateSum(a,b);
        int product=calculateProduct(a, b);
        int remain=calculateRemain(a, b);
        System.out.println("sum of 2 numbers is:"+ sum);
        System.out.println("Product of 2 numbers is:"+ product);
        System.out.println("Remainder of 2 numbers is:"+ remain);
        sc.close();
    }
    
}
