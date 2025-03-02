public class Practice {
    public static void printDecreasing(int n){
        if(n==1){
            System.out.println(n);
            return;
        }else{
        System.out.print(n+" ");
        printDecreasing(n-1);
        }
    }

    public static void printIncreasing(int n){
        if(n==1){
            System.out.print(n+" ");
            return;
        }else
         {printIncreasing(n-1);
         System.out.print(n+" ");
         }
        }
    
    public static void main(String[] args) {
       // printDecreasing(10);
        printIncreasing(10);
        
    }
    
}
