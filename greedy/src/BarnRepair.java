import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Gap implements Comparable<Gap> {
    int start,end,size;

    Gap(int s, int e){
        start = s;
        end = e;
        size = size();
    }

    public int size(){
        return end - start - 1;
    }

    public int compareTo(Gap g){
        return size - g.size;
    }
}

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO
        Arrays.sort(occupied);
        ArrayList<Gap> gaps = new ArrayList<>();
        for(int i = 1;i < occupied.length;i++){
            if(occupied[i] - occupied[i-1] > 1){
                gaps.add(new Gap(occupied[i-1], occupied[i]));
            }
        }
        Collections.sort(gaps);

        int boards = gaps.size() + 1;
        int stalls_covered = occupied.length;
        while(boards > M){
            Gap g = gaps.remove(0);
            stalls_covered += g.size;
            boards --;
        }


        return stalls_covered;
    }
}