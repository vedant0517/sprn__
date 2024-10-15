public class pattern7 {
    public static void main(String args[]){
     // nested loop for() floyed triangle

     int n=5;
     int number=1;

        //outerloop for rows
         for(int i=1; i<=n; i++) {
        //inner loop for columns
           for(int j=1; j<=i; j++){
             System.out.print(number+" ");
             number++;//number = number+1
            }System.out.println();

    
     }
  }
}