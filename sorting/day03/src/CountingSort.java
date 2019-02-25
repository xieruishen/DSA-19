public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+k)
     * Space complexity: O(k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // TODO
        if(A.length == 0){
            return;
        }
        int k = A[0];
        for (int i = 1; i < A.length; i++){
            if (A[i]>k){
                k = A[i];
            }
        }

        int[] counts = new int[k+1];
        for (int i=0; i<A.length;i++){
            counts[A[i]]++;
        }

        int index = 0;
        for (int p = 0; p<k+1; p++){
            while(counts[p]>0){
                A[index] = p;
                counts[p]--;
                index++;
            }
        }

    }

}
