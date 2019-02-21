import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        int[] sum = new int[nums.length];
        int maxsize = -1, startindex = 0, endindex = 0;
        if (nums[0] == 0) {
            sum[0] = -1;
        } else {
            sum[0] = nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum[i] = sum[i - 1] - 1;
            } else {
                sum[i] = sum[i - 1] + 1;
            }
            if (sum[i] == 0) {
                maxsize = i + 1;
                endindex = i;
            }
        }


        HashMap<Integer, Integer> sums = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            if (sums.containsKey(sum[i])) {
                if (maxsize < i - sums.get(sum[i])) {
                    maxsize = i - sums.get(sum[i]);
                    startindex = sums.get(sum[i]) + 1;
                    endindex = i;
                }
            } else {
                sums.put(sum[i], i);
            }
        }
        if (maxsize == -1) {
            return null;
        } else {
            return new int[] {startindex,endindex};
        }
    }
}