import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        this.tiles = b;
        this.n = b.length;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // TODO: Your code here
        int sum = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                int correct_num;
                if(i==n-1 && j== n-1){
                    correct_num = 0;
                }
                else{
                    correct_num = n*i+j+1;
                }
                if (tiles[i][j] == correct_num){
                    continue;
                }
                int desired_i;
                int desired_j;
                if (tiles[i][j] != 0){
                    desired_i = (tiles[i][j] - 1)/n;
                    desired_j = (tiles[i][j] - 1) % n;
                    sum += Math.abs(desired_i-i) + Math.abs(desired_j - j);
                }
            }
        }
        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        for(int i = 0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                int correct_num;
                if (i == n - 1 && j == n - 1) {
                    correct_num = 0;
                } else {
                    correct_num = n * i + j + 1;
                }
                if (tiles[i][j] != correct_num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int mergeSortHelper(int[] arr, int[] temp, int left, int right)
    {
        int mid, inv_count = 0;
        if (right > left) {
            mid = (right + left) / 2;
            inv_count = mergeSortHelper(arr, temp, left, mid);
            inv_count += mergeSortHelper(arr, temp, mid + 1, right);
            inv_count += merge(arr, temp, left, mid + 1, right);
        }
        return inv_count;
    }

    public static int merge(int arr[], int temp[], int left, int mid, int right)
    {
        int i, j, k;
        int inv_count = 0;

        i = left;
        j = mid;
        k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
                inv_count = inv_count + (mid - i);
            }
        }

        while (i <= mid - 1)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return inv_count;
    }

    public static int mergeSortCount(int[] arr)
    {
        int[] temp = new int[arr.length];
        return mergeSortHelper(arr, temp, 0, arr.length - 1);
    }


    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        int[] puzzle_flat = new int[n*n-1];
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j ++){
                if(tiles[i][j] != 0){
                    puzzle_flat[counter] = tiles[i][j];
                    counter++;
                }
            }
        }
        int inv_count = 0;
        inv_count = mergeSortCount(puzzle_flat);

//        for(int i = 0; i < n*n -2;i++){
//            for(int j = i + 1; j < n*n-1;j++){
//                if(puzzle_flat[i] > puzzle_flat[j]){
//                    inv_count ++;
//                }
//            }
//        }
        if(inv_count % 2 == 0){
            return true;
        }
        return false;
    }

    /*
     * Return all neighboring boards in the state tree
     */

    public int[][] copyof(int[][] A){
        int[][] B = new int[A.length][A.length];
        for(int i=0; i<A.length;i++){
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        }
        return B;
    }

    public Iterable<Board> neighbors() {
        // TODO: Your code here
        LinkedList<Board> neighbors = new LinkedList<>();
        outerloop:
        for(int i= 0; i<n;i++){
            for(int j = 0; j<n; j++){
                if(tiles[i][j] == 0){
                    // move 0 right
                    if(j >= 0 && j < n - 1){
                        int[][] temp = copyof(tiles);
                        temp[i][j] = tiles[i][j + 1];
                        temp[i][j+1] = 0;
                        neighbors.add(new Board(temp));
                    }
                    // move 0 left
                    if(j < n && j > 0){
                        int[][] temp = copyof(tiles);
                        temp[i][j] = tiles[i][j - 1];
                        temp[i][j-1] = 0;
                        neighbors.add(new Board(temp));
                    }
                    // move 0 down
                    if(i >= 0 && i < n - 1){
                        int[][] temp = copyof(tiles);
                        temp[i][j] = tiles[i + 1][j];
                        temp[i + 1][j] = 0;
                        neighbors.add(new Board(temp));
                    }

                    // move 0 up
                    if(i < n && i > 0){
                        int[][] temp = copyof(tiles);
                        temp[i][j] = tiles[i - 1][j];
                        temp[i - 1][j] = 0;
                        neighbors.add(new Board(temp));
                    }
                    break outerloop;
                }
            }
        }
        return neighbors;
    }

    public void print_board(){
        for(int i = 0; i < n; i++){
            System.out.println("");
            for(int j = 0; j < n; j++){
                System.out.print(tiles[i][j]);
                System.out.print(", ");
            }
        }
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
        Board board = new Board(initState);
        board.print_board();

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for(Board i:it){
            i.print_board();
            System.out.println(" ");
        }
    }
}
