import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        List<List<Integer>> permutations = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        List<Integer> unused = new LinkedList<>();
        for(int i:A){
            unused.add(i);
        }
        permutations_helper(current,unused,permutations);
        return permutations;
    }

    public static void permutations_helper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations){
        if(unused.isEmpty()){
            permutations.add(new LinkedList<>(current));
            return;
        }
        for (int c : new LinkedList<>(unused)) {
            current.add(c);
            unused.remove(0);
            permutations_helper(current,unused,permutations);
            current.remove(current.size()-1);
            unused.add(c);
        }
    }

}
