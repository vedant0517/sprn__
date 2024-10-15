public class pattern3 {
    public static void main(String args[]){
// nested loop for()inverted half pyramid

int n=4;

//outerloop for rows
for(int i=n; i>=1; i--){
    //inner loop for columns
    for(int j=1;j<=i;j++){
        System.out.print("*");
    }System.out.println();
}

    
 }
}

