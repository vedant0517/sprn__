import java.util.Scanner;
//Q1:Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
// #DSA-4
public class ass1 {
    public static boolean duplicateNum(int nums[]){
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    return true;
            }
        }
    }
    return false;
    }
    public static void main(String[] args) {
        int nums[]=new int[10];
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter 10 values");
        for(int i=0;i<nums.length;i++){
            nums[i]=sc.nextInt();
        }
        System.out.println(duplicateNum(nums));
        sc.close();
        
    }

    }    

