public class fib {
 
    public static int getFib(int n){
            if(n==0 || n==1){
                return n;
            }else{
                return getFib(n-1)+getFib(n-2);
            }
        }
    
    
     public static void main(String[] args) {
         int n=26;
        System.out.println(getFib(n));
        }
        
    }
    
    

