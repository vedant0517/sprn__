public class assignment {

    public static boolean isSafe(int maze[][],int x,int y){
        //return false if x and y goes out of scope
        return (x>=0 && x<maze.length && y>=0 && y<maze.length && maze[x][y]==1);
    }

    public static boolean solveMaze(int maze[],int x,int y,int sol[][]){

    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        int maze[][] = { { 1, 0, 0, 0 },
        {1,1,0,1},{0,1,0,0},{1,1,1,1}};


    }
}
