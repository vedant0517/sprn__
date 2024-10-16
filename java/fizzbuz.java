import java.util.*;
public class fizzbuz {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Number");
        int num=sc.nextInt();

        if((num % 3 ==0)&&(num %5 ==0))//multiple of 3 and 5
        {
            System.out.println("FizzBuzz");
        }else if (num % 3 ==0)//only multiple of 3
        {
            System.out.println("Fizz");
        }else if (num % 5 ==0)//only multiple of 5

        {
            System.out.println("Buzz");
        }else{
            System.out.println(num);
        }sc.close();
        
         
        
    }
}


