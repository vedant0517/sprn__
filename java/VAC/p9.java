public class p9 {
    static int N = 4;
    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;
        return true;
    }
    static void solve(int[][] board, int row) {
        if (row == N) {
            print(board);
            return;
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                solve(board, row + 1);
                board[row][col] = 0; // backtrack
            }
        }
    }
    static void print(int[][] board) {
        for (int[] row : board) {
            for (int x : row)
                System.out.print(x + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[][] board = new int[N][N];
        solve(board, 0);
    }
}