
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlgn)
     * Worst-case runtime: O(nlgn)
     * Average-case runtime: O(nlgn)
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        int[] sorted = new int[array.length];
        int l = 0;
        int r = array.length-1;
        if (l < r){
            int m = (int)Math.ceil((double)(l + r)/2);
            int[] left = new int[m];
            int[] right = new int[array.length-m];
            for (int i = 0; i < m; i++){
                left[i] = array[i];
            }
            for (int j = 0; j < array.length-m;j++){
                right[j] = array[m+j];
            }
            int[] sortedl = sort(left);
            int[] sortedr = sort(right);
            sorted = merge(sortedl,sortedr);

        }
        else {
            sorted = array;
        }
        return sorted;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length+b.length];
        int i = 0; // index of first array
        int j = 0; // index of second array
        int k = 0; // index of sorted array
        while (i < a.length && j < b.length){
            if (a[i] <= b[j]){
                merged[k] = a[i];
                i ++;
            }
            else{
                merged[k] = b[j];
                j ++;
            }
            k++;
        }
        while (i < a.length){
            merged[k] = a[i];
            i ++;
            k ++;
        }
        while (j < b.length){
            merged[k] = b[j];
            j ++;
            k ++;
        }
        return merged;
    }

}
