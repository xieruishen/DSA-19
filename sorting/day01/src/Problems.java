import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        System.out.println("sol:");
        for(int k = 0; k<out.length;k++){
            System.out.print(out[k]);
            System.out.print(", ");
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO
        if (inputStream.length == 0){
            return runningMedian;
        }
        PriorityQueue<Integer> leftmedian = maxPQ();
        PriorityQueue<Integer> rightmedian = minPQ();
        runningMedian[0] = (double) inputStream[0];

        if (inputStream.length == 1){
            return runningMedian;
        }

        if (inputStream[1] >= inputStream[0]){
            leftmedian.offer(inputStream[0]);
        }
        else {
            rightmedian.offer(inputStream[0]);
        }

        for (int i = 1; i < inputStream.length;i++){
            if (i%2 != 0){ // number of seen element odd
                if (inputStream[i] <= runningMedian[i-1] && leftmedian.size() < rightmedian.size()){
                    leftmedian.offer(inputStream[i]);
                    double num = leftmedian.peek();
                    runningMedian[i] = (runningMedian[i-1] + num)/2;
                }
                else if(inputStream[i] <= runningMedian[i-1] && leftmedian.size() > rightmedian.size()){
                    int temp = leftmedian.poll();
                    rightmedian.offer(temp);
                    leftmedian.offer(inputStream[i]);
                    double num = leftmedian.peek();
                    runningMedian[i] = (runningMedian[i-1] + num)/2;
                }
                else if(inputStream[i] > runningMedian[i-1]&& leftmedian.size() > rightmedian.size()){
                    rightmedian.offer(inputStream[i]);
                    double num = rightmedian.peek();
                    runningMedian[i] = (runningMedian[i-1] + num)/2;
                }
                else {
                    int temp = rightmedian.poll();
                    leftmedian.offer(temp);
                    rightmedian.offer(inputStream[i]);
                    double num = rightmedian.peek();
                    runningMedian[i] = (runningMedian[i-1] + num)/2;
                }
            }
            else{
                if (inputStream[i] <= runningMedian[i-1]){
                    leftmedian.offer(inputStream[i]);
                    double num = leftmedian.peek();
                    runningMedian[i] = num;
                }
                else{
                    rightmedian.offer(inputStream[i]);
                    double num = rightmedian.peek();
                    runningMedian[i] = num;
                }
            }
        }
        for(int k = 0; k<runningMedian.length;k++){
            System.out.print(runningMedian[k]);
            System.out.print(", ");
        }
        return runningMedian;
    }

}
