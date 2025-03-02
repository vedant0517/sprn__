//Time complexity and sapce complexity is Big(n);

public class sumNat {
    
     public static int calcSum(int n){
        if(n==1){
             return 1;
        }else{
             return n+calcSum(n-1);
        }
    }
    
        public static void main(String[] args) {
            int n=3;
            System.out.println("Sum of first "+n+"th natural numer : "+calcSum(n));
        }
        
    }
    
    

