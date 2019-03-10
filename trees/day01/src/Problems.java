import java.util.ArrayList;

public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        countingSort(A);
        ArrayList<Integer> num1 = new ArrayList<>();
        ArrayList<Integer> num2 = new ArrayList<>();
        for (int i = 0; i< A.length;i+=2)
        int first = 0;
        int second = 0;
        for (int j = num1.size()-1; j>=0;j--){
            first += Math.pow(10,num1.size()-1-j)*num1.get(j);
        }
        for (int k = num2.size()-1; k>=0;k--){
            second += Math.pow(10,num2.size()-1-k)*num2.get(k);
        }
        return first+second;
    }

    static void countingSort(int[] A) {
        if (A.length == 0) {{
            num1.add(A[i]);
            if(i+1 < A.length){
                num2.add(A[i+1]);
            }
        }
            return;
        }
        int[] counts = new int[10];
        for (int i = 0; i < A.length; i++) {
            counts[A[i]]++;
        }

        int index = 0;
        for (int p = 0; p < 10; p++) {
            while (counts[p] > 0) {
                A[index] = p;
                counts[p]--;
                index++;
            }
        }

    }
}