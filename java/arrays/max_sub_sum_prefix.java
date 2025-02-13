//Prefix sum
public class max_sub_sum_prefix {
    public static void maxSubarraySumPrefix(int num[]){
        int prefix[]=new int[num.length];
       int currSum=0;
       int maxSum=Integer.MIN_VALUE;
       prefix[0]=num[0];

       // calculate prefix array
        for(int i=1;i<prefix.length;i++){
            prefix[i]=prefix[i-1]+num[i];
        }
            for(int i=0;i<num.length;i++){
                int start=i;
                for(int j=i;j<num.length;j++){
                    int end=j;
                    currSum = start == 0 ? prefix[end] : prefix[end] - prefix[start-1];
                    
                    if(currSum>maxSum){
                        maxSum=currSum;
                    }
                  }
               }
               System.out.println("Max Sum of Subarrays = " +maxSum);
        }
 public static void main(String[] args) {
    int num[]={1,-2,6,-1,3};
    maxSubarraySumPrefix(num);
 }  
}

