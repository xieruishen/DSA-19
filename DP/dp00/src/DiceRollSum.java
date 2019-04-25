import java.util.HashMap;

public class DiceRollSum {

    // Runtime: TODO
    // Space: TODO
    public static int diceRollSum(int N) {
        // TODO
        int [] DP = new int[N+1];
        for(int i = 0; i < DP.length;i++){
            DP[i] = -1;
        }
        return diceRollSumHelper(N,DP);
    }

    public static int diceRollSumHelper(int n, int[] DP){
        // check if solution exist
        if (DP[n] != -1) return DP[n];
        // base case
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        // recurse relation
        int total_sum = 0;
        for(int i = 1; i < 7;i++){
            if(n >= i){
                total_sum += diceRollSumHelper(n-i,DP);
            }
        }
        DP[n] = total_sum;
        return total_sum;

    }

}