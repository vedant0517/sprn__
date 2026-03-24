public class fact{
    public static void main(String[] args) {
        // int n=5;
        // int x=factorial(n);
        // System.out.println("Factorial of "+n+":"+x);
        // asci();
        sz();
    }

    public static int factorial(int n){
        if(n==0 || n==1) return 1;
        return n*factorial(n-1);
    }

    public static void asci(){
        char ch='a';
        int x=ch;

        System.out.println("Asci Value of "+ch+":"+x);
    }


    public static void sz(){
        
        int a=1;
        int b=1;
       //System.out.println("Size of byte: " + Byte.BYTES + " bytes.");
       // System.out.println("Size of short: " + Short.BYTES + " bytes.");
        System.out.println("Size of int: " + Integer.BYTES + " bytes.");
        System.out.println("Size of long: " + Long.BYTES + " bytes.");
        System.out.println("Size of char: " + Character.BYTES + " bytes.");
        System.out.println("Size of float: " + Float.BYTES + " bytes.");
        System.out.println("Size of double: " + Double.BYTES + " bytes.");

        System.out.println(a++);
        System.out.println(++b);
    }

}