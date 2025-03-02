public class ExceptionHanding {
    public static int divide(int a,int b){
        return a/b;

    }
    public static void main(String argz[]){
        try{
            int result = divide(10,10);
            System.out.println("Division :"+result);
        }
        catch(ArithmeticException e){
            System.out.println("Exception occured:cannot divide by 0");
        }finally{
            System.out.println("Executed Successfully");
        }

    }
    
}
