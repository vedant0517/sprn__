import java.util.*;
//Brute Force I-1
public class max_sub_sum_brute {
    public static void maxSubarraySum(int num[]){
        int currSum=0;
        int maxSum=Integer.MIN_VALUE;
     
        for(int i=0;i<num.length;i++){
            // int start=i;
            for(int j=i;j<num.length;j++){
                //int end=j;
                currSum=0; //mandatory bcoz value will change in every iteration
                for(int k=i;k<=j;k++){
                    currSum+=num[k];//subarrays sum
                }
                System.out.print(currSum+" ");
                System.out.println();
                if(currSum>maxSum){
                    maxSum=currSum;
                }
            }
        }System.out.println("Max Sum of Subarrays = " +maxSum);

    }
 public static void main(String[] args) {
    int num[]=new int[10];
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter 10 values");
    for(int i=0;i<num.length;i++){ 
        num[i]=sc.nextInt();
    }sc.close();
    maxSubarraySum(num);
 }  
}