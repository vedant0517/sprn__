// public class Pratice {
//     public static void main(String[] args){ 
//         for(int i=0; i<5; i++) {
//     System.out.println("Hello");
//      i+=2;
//     }
//     }
//     }

// import java.util.*;
// public class Pratice{
//     public static void main(String args[]){
//         Scanner sc=new Scanner(System.in);
//         System.out.print("Enter number to check  ");
//         int num=sc.nextInt();

//         if(num>=0)
//         {
//             System.out.println("Positive");
//         } else
//         {
//                 System.out.println("Negative");
//         }sc.close();
//         }

//     }
import java.util.*;
public class Pratice{
    public static void printHello(){
        System.out.println("Hello World");
    }
    public static void calculateSum(){
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter Number a and b");
    int a=sc.nextInt();
    int b=sc.nextInt();
    int sum=a+b;
    System.out.println("Sum is "+sum);
      sc.close();
    }
    public static void main(String args[]){
        printHello();
        calculateSum();
        }

    }

