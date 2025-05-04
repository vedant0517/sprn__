public class MazeSolver {

    // Print the solution path
    static void printSolution(int[][] path) {
        for (int[] row : path) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Check if the move is within bounds and the cell is 1 (open)
    static boolean isSafe(int[][] maze, int x, int y) {
        int n = maze.length;
        
        return x >= 0 && y >= 0 && x < n && y < n && maze[x][y] == 1;
    }

    // Solving function (recursively explores all 4 directions)
    static boolean solveMaze(int[][] maze, int x, int y, int[][] path) {
        int n = maze.length;

        // If goal is reached
        if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
            path[x][y] = 1;
            printSolution(path);
            return true;
        }

        if (isSafe(maze, x, y) && path[x][y] == 0) {
            path[x][y] = 1;

            // Move right
            if (solveMaze(maze, x, y + 1, path)) return true;

            // Move down
            if (solveMaze(maze, x + 1, y, path)) return true;

            // Move left
            if (solveMaze(maze, x, y - 1, path)) return true;

            // Move up
            if (solveMaze(maze, x - 1, y, path)) return true;

            // Backtrack
            path[x][y] = 0;
        }

        return false;
    }

    // Main method
    public static void main(String[] args) {
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };

        int n = maze.length;
        int[][] path = new int[n][n];

        if (!solveMaze(maze, 0, 0, path)) {
            System.out.println("No solution found.");
        }
    }
}
