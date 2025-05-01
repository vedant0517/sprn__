public class sudokuSolver {
    public static boolean suduko(int board[][],int row,int col){
        //bae case
        if(row==9){
            return true;
        }
        //recursion
        int nextRow=row,nextCol=col+1;
        if(col+1==9){
            nextRow=row+1;
            nextCol=0;
        }
        if(board[row][col] != 0){
            return suduko(board, nextRow, nextCol);
        }

        for(int digit=1;digit<=9;digit++){
            if(isSafe(board,row,col,digit)){
                board[row][col]=digit;
                if(suduko(board,nextRow, nextCol))//Solution exist
                  {
                    return true;
                  }
                  board[row][col]=0;//backtracking
            }
        }
        return false;
    }


    public static boolean isSafe(int board[][],int row,int col,int digit){
        //row case
        for(int i=0;i<9;i++){
            if(board[row][i]==digit) return false; 
        }
        //for column xase
        for(int i=0;i<9;i++){
            if(board[i][col]==digit) return false; 
        }

        //for 3*3 box case
        int startrow=(row/3)*3;
        int startcol=(col/3)*3;
        for(int i=startrow;i<startrow+3;i++){
            for(int j=startcol;j<startcol+3;j++){
                if(board[i][j]==digit){
                    return false;
                }
             }
          }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row <9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("----------+-----------+-----------");
            }
            for (int col = 0; col <9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print(" | ");
                }
                System.out.print(" " + board[row][col] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int board[][]={
    {0,0,8,0,0,0,0,0,0},
    {4,9,0,1,5,7,0,0,2},
    {0,0,3,0,0,4,1,9,0},
    {1,8,5,0,6,0,0,2,0},
    {0,0,0,0,2,0,0,6,0},
    {9,6,0,4,0,5,3,0,0},
    {0,3,0,0,7,2,0,0,4},
    {0,4,9,0,3,0,0,5,7},
    {8,2,7,0,0,9,0,1,3}};
    if(suduko(board, 0, 0)){
    System.out.println("Solution exists");
    printBoard(board);
    }else{
    System.out.println("Not exist");}
}
}
