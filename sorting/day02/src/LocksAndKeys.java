public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    private static int partition(char[] arr, int low, int high, char pivot)
    {
        int i = low;
        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot){
                swap(arr,i,j);
                i++;
            } else if(arr[j] == pivot){
                swap(arr,j,high);
                j--;
            }
        }
        swap(arr,i,high);
        return i;
    }
    private static void matchPairs(char[] locks, char[] keys, int low,
                                   int high)
    {
        if (low < high)
        {
            // Choose last character of keys array for locks partition.
            int pivot = partition(locks, low, high, keys[high]);

            // Now using the partition of locks choose that for keys partition.
            partition(keys, low, high, locks[pivot]);

            matchPairs(locks, keys, low, pivot-1);
            matchPairs(locks, keys, pivot+1, high);
        }
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        matchPairs(locks,keys,0,keys.length-1);
        result[0] = locks;
        result[1] = keys;
        return result;
    }
}




