public class LongestIncreasingSubsequence {

    // Runtime: n
    // Space: n

    public static int LIS(int[] A) {
        // TODO
        if(A.length == 0){
            return 0;
        }
        int[] DP = new int[A.length];
        for(int i = 0; i < A.length; i++){
            DP[i] = -1;
        }
        int max = 1;
        for(int j = A.length - 1; j >= 0; j--){
            int length = LIShelper(A,j,DP);
            if (length > max){
                max = length;
            }
        }
        return max;
    }

    public static int LIShelper(int[] A, int index, int[] DP){
        if(DP[index] != -1){
            return DP[index];
        }
        // base case
        if(index == A.length-1){
            DP[index] = 1;
            return 1;
        }
        int max = 1;
        int next_increase_index = index + 1;
        while(next_increase_index < A.length){
            if(A[next_increase_index] > A[index]){
                int length = 1 + LIShelper(A,next_increase_index,DP);
                if(length > max){
                    max = length;
                }
            }
            next_increase_index ++;
        }
        DP[index] = max;

        return max;
    }
}