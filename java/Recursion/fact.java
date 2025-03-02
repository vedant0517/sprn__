//Time complexity and sapce complexity is Big(n);

public class fact {
    public static long calcFact(long n){
        if(n==0){
            return 1;
        }else{
            return n*calcFact(n-1);
        }
    }

    public static void main(String[] args) {
        long n=15;
        System.out.println("factorial of "+n+" : "+calcFact(n));
    }
    
}
