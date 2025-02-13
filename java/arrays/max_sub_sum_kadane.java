//Kadane Algorithm
public class max_sub_sum_kadane {
    public static void maxSubarraySumKadane(int num[]){
       int currSum=0;
       int maxSum=Integer.MIN_VALUE;
            for(int i=0;i<num.length;i++){
                currSum=currSum+num[i];
                    if(currSum<0){
                    currSum=0;  
                  }
                  maxSum=Math.max(currSum,maxSum);
               }
        System.out.println("Max Sum of Subarrays : " +maxSum);
        }
 public static void main(String[] args) {
    int num[]={-2,-3,4,-1,-2,1,5,-3};
    maxSubarraySumKadane(num);
 }  
}

