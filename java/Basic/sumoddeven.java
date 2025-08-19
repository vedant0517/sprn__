import java.util.*;
public class sumoddeven {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number;
        int evensum=0;
        int oddsum=0;
        int choice;
        do{
            System.out.println("Enter number to calculate sum");
            number=sc.nextInt();
            if(number%2==0){
                evensum+=number;
            }else{
                oddsum+=number;
            }
    System.out.print("Do you want to continue? Press 1 for yes or 0 for no");
    choice = sc.nextInt();
        }while(choice==1);
        System.out.println("Sum of odd number is:"+ oddsum);
        System.out.println("Sum of even number is:"+ evensum);
        sc.close();
    }

}
