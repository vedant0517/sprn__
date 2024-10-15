public class pattern2 {
    public static void main(String args[]){
// nested loop for() half pyramid
 
int n=4;

//outerloop for rows
for(int i=1; i<=n; i++){
    //inner loop for columns
    for(int j=1;j<=i;j++){
        System.out.print("*");
    }System.out.println();
    }
  }
}
