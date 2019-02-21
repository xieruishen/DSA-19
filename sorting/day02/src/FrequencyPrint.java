import java.util.HashMap;
import java.util.*;
import java.lang.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        String[] input = s.split(" ");
        HashMap<String, Integer> histogram = new HashMap<>();
        for(int i = 0; i<input.length;i++){
            histogram.put(input[i],histogram.getOrDefault(input[i],0)+1);
        }
        ArrayList<HashMap.Entry<String, Integer>> his = new ArrayList<>(histogram.entrySet());
        class SortbyHis implements Comparator<HashMap.Entry<String,Integer>>{
            public int compare(HashMap.Entry<String, Integer> s1, HashMap.Entry<String, Integer> s2){
                return (s1.getValue()).compareTo(s2.getValue());
            }
        }

        Collections.sort(his, new SortbyHis());
        String result = "";
        for(int i = 0; i<his.size();i++){
            for (int j = 0; j<his.get(i).getValue(); j++){
                result = result + his.get(i).getKey()+" ";
            }
        }
        result.trim();
        return result;
    }

}
