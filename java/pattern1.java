public class pattern1 {
    public static void main(String args[]){
// nested loop for() hollow rectangle 
int n=4;
int m=5;
//outerloop for rows
for(int i=1;i<=n;i++){
    //innerloop for column
    for( int j=1;j<=m;j++){
        //cell -> (i,j)
        if(i == 1 || j == 1 || i == n || j == m){
            System.out.print("*");
        }else{
            System.out.print(" ");
        }
    }System.out.println();
    
    }
    }
}
