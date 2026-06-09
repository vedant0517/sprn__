public class test{
    public static void main(String[] args){
        int n = 3;
        for (int i = 1; i <= n; i++) {
            // Print spaces
            for (int j = i; j < n; j++) {
                System.out.print("  ");
            }
            // Print stars
            for (int j = 1; j < 2 * i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
/*        *
        * * *
      * * * * *  */