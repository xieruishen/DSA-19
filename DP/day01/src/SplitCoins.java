public class SplitCoins {

    public static int splitCoins(int[] coins) {
        // TODO
        // find sum of all element
        int sum = 0;
        for(int i = 0; i < coins.length;i++){
            sum += coins[i];
        }
        boolean[][] DP = new boolean[coins.length+1][sum+1];

        // Build first row
        for(int col = 1; col < sum+1;col++){
            DP[0][col] = false;
        }
        // Build first column
        for(int row = 0; row < coins.length+1;row++){
            DP[row][0] = true;
        }

        // build table
        for(int s = 1;s < sum+1;s++){
            for(int n = 1; n < coins.length+1;n++){
                // not include the nth element
                DP[n][s] = DP[n-1][s];

                // include the nth element (if it is possible to reach the sum - num without the number)
                if(coins[n - 1] <= s){
                    DP[n][s] |= DP[n - 1][s - coins[n - 1]];
                }
            }

        }

        int diff = Integer.MAX_VALUE;
        for(int k = sum/2; k >= 0; k--){
            if(DP[coins.length][k]){
                diff = sum - 2*k;
                break;
            }
        }

        return diff;
    }
}
