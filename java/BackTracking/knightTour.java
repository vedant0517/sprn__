public class knightTour {
    static final int N = 8;

    // Check if the knight can move to position (x, y)
    static boolean isSafe(int x, int y, int[][] board) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    // Print the chessboard with the knight's moves
    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%2d ", board[i][j]);
            System.out.println();
        }
    }

    // The main function to solve the Knight's Tour (without using another function)
    static boolean solveKnightTour() {
        int[][] board = new int[N][N];

        // Initialize all cells to -1 (unvisited)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = -1;

        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

        // Start from the top-left corner
        board[0][0] = 0;

        // Use a simple loop with manual stack-style logic (iterative backtracking is complex)
        // So we use recursion directly inside this function
        if (solveFrom(0, 0, 1, board, xMove, yMove)) {
            printBoard(board);
            return true;
        } else {
            System.out.println("Solution does not exist.");
            printBoard(board); return false;
        }
    }

    // Inline recursive logic (normally in solve()) moved here
    static boolean solveFrom(int x, int y, int moveCount, int[][] board, int[] xMove, int[] yMove) {
        if (moveCount == N * N)
            return true;

        for (int k = 0; k < 8; k++) {
            int nextX = x + xMove[k];
            int nextY = y + yMove[k];
            if (isSafe(nextX, nextY, board)) {
                board[nextX][nextY] = moveCount;
                if (solveFrom(nextX, nextY, moveCount + 1, board, xMove, yMove))
                    return true;
                board[nextX][nextY] = -1; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        solveKnightTour();
    }
}