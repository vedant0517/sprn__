public class pattern4 {
    public static void main(String args[]){
// nested loop for()inverted half pyramid

int n=4;

//outerloop for rows
for(int i=1;i<=n;i++){
    //inner loop-space print
for(int j=1;j<=n-i;j++){
    System.out.print(" ");
} 
//inner loop-star print
for(int j=1;j<=i;j++){
    System.out.print("*");
}System.out.println();

}
 
 }
}