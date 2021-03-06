import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// runtime:n! (number of calls) total possible solutions each solution has n^2 cost
public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    /**
     * check for a given column whether each row above a desired row has a queen
     */
    public static boolean checkColumn(char[][] board, int r, int c){
        for(int i = 0; i < r;i++){
            if(board[i][c] == 'Q') return true;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        nQueenHelper(board,0,answers,n);
        return answers;
    }

    public static void nQueenHelper(char[][] board, int row, List<char[][]> answers,int N){
        if(row == N){
            answers.add(copyOf(board));
            return;
        }
        for(int col = 0; col < N;col++){
            board[row][col] = 'Q';
            if(!(checkColumn(board,row,col) || checkDiagonal(board,row,col))){
                nQueenHelper(board,row+1,answers,N);
            }
            board[row][col] = '.';
        }
    }

}
