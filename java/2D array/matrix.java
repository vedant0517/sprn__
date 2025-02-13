// import java.util.*;

// public class matrix{
//     public static void main(String argz[]){

//         int matrix[][]=new int[3][3];
       
//         int i,j;
//         Scanner sc=new Scanner(System.in);
//         for(i=0;i<3;i++){
//             for(j=0;j<3;j++){
//                 matrix[i][j]=sc.nextInt();
//             }
//         }
//          int max=matrix[0][0];
//         int min=matrix[0][0];
//         //output
        
//         for(i=0;i<3;i++){
//             for(j=0;j<3;j++){
//                System.out.print(matrix[i][j]+" ");
//             }
//             System.out.println();
//         }
         
//         for(i=0;i<3;i++){
//             for(j=0;j<3;j++){
//                 if(max<matrix[i][j]){
//                     max=matrix[i][j];
//                 }
//                 if(min>matrix[i][j]){
//                     min=matrix[i][j];
//                 }
//             }
//         }

//         System.out.println("Max : "+max);
//         System.out.println("Min : "+min);
    
// sc.close();


//     }
// }

import java.util.*;

public class matrix{

    public static void printSpiral(int matrix[][]){
        int startRow=0,endRow=matrix.length-1,startCol=0,endCol=matrix[0].length-1;
        while(startRow<=endRow && startCol<=endCol){
            //top
            for(int j=startCol;j<=endCol;j++){
                System.out.print(matrix[startRow][j]+" ");
            }
            //right
            for(int i=startRow+1;i<=endRow;i++){
                System.out.print(matrix[i][endCol]+" ");
            }
            //bottom
            for(int j=endCol-1;j>=startCol;j--){
                System.out.print(matrix[endRow][j]+" ");
            }
            //left
            for(int j=endRow-1;j>startRow;j--){
                System.out.print(matrix[j][startCol]+" ");
        }
        startRow++;
        startCol++;
        endCol--;
        endRow--;
    }
    System.out.println();
    }


    public static int diagonalSum(int matrix[][]){
        int sum=0;
        for(int i=0;i<matrix.length;i++){
            sum+=matrix[i][i];

            if(i != matrix.length-i-1){
                sum+=matrix[i][matrix.length-i-1];
            }
        }
        return sum;
  
    }
    
    public static void main(String argz[]){

        int matrix[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printSpiral(matrix);
        System.out.println("Diagonal sum : "+diagonalSum(matrix));
       
    }
}

