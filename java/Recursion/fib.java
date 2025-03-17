public class fib {
 
    public static int getFib(int n){
            if(n==0 || n==1){
                return n;
            }else{
                return getFib(n-1)+getFib(n-2);
            }
        }

        public static void fact(int n){
            int t1=0,t2=1,nextTerm;
            System.out.print("Fibonacci Series: ");
            for(int i=0;i<=n;i++){
                System.out.print(t1+" ");
                nextTerm=t1+t2;
                t1=t2;
                t2=nextTerm;

            }
        }
    
    
     public static void main(String[] args) {
         int n=10;
       // System.out.println(getFib(n));
      // fact(10);
       System.out.print("Fibonacci Number: ");
       for(int i=0;i<=n;i++){
        System.out.print(getFib(i)+" ");


        }
        
    }
}
    
    

