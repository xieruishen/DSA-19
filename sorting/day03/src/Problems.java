import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        int[] nonnegative = new int[A.length];
        int count_nonnegative = 0;
        int[] negative = new int[A.length];
        int count_negative = 0;
        for (int i : A){
            if (i<0){
                negative[count_negative] = Math.abs(i);
                count_negative ++;
            }
            else{
                nonnegative[count_nonnegative] = i;
                count_nonnegative ++;
            }
        }

        RadixSort.radixSort(nonnegative,10);
        RadixSort.radixSort(negative,10);

        int index = 0; // keep track of index in A
        for(int i = A.length - 1; i > A.length-1-count_negative; i--){
            A[index] = -negative[i];
            index ++;
        }
        for(int j = A.length - count_nonnegative; j < A.length; j++){
            A[index] = nonnegative[j];
            index ++;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        int b = 26;
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++) {
            L[i] = new LinkedList<>();
        }
        for(String i : A){
            L[getNthCharacter(i,n)].add(i);
        }
        int k = 0;
        for (LinkedList<String> list : L) {
            while(list.size()>0){
                A[k]=list.getFirst();
                k++;
                list.removeFirst();
            }
        }
    }


    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
        for (int i=0; i< stringLength;i++){
            countingSortByCharacter(S,i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
