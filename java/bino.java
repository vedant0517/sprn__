public class bino{

   public static int calculateFact(int f){
       //loop
       int factorial=1;
       for(int i=f; i>=1;i--) 
        factorial *= i;   
       return factorial;
    }
    
        
    public static int binCoe(int n,int r){
        int factN = calculateFact(n);
        int  factR = calculateFact(r);
        int factNMR = calculateFact(n-r);
        int binCoe = factN/(factR*factNMR);       
         return binCoe;
     }
     public static void main(String[] args) {
        System.out.println(binCoe(5,2));
        }

    
}

