public class pattern10 {
    public static void main(String args[]){
// nested loop for() character pattern
int n=4;
char ch='A'; //always write char in single inverted comma
//outerloop for rows
for(int i=1; i<=n; i++){
    //inner loop for columns
    for(int j=1;j<=i;j++){
        System.out.print(ch);
        ch++;
    }System.out.println();
    }
  }
}
