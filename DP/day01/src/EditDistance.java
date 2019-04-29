import java.util.HashMap;

public class EditDistance {

    public static int LevenshteinDistance(String a, String b, int[][] DP){
        int i = a.length();
        int j = b.length();
        if(DP[i][j] != -1){
            return DP[i][j];
        }
        // base case
        if(i == 0){
            return j;
        }
        if(j == 0) {
            return i;
        }

        // test if last characters of the strings match
        int cost;
        if (a.charAt(a.length()-1) == b.charAt(b.length()-1))
            cost =  0;
        else
            cost = 1;

        // deletion from a
        int min = LevenshteinDistance(a.substring(0,a.length()-1), b, DP) + 1;
        // deletion from b
        int distance2 = LevenshteinDistance(a,b.substring(0,b.length()-1),DP) + 1;
        if(distance2 < min){
            min = distance2;
        }
        // delete from both
        int distance3 = LevenshteinDistance(a.substring(0,a.length()-1),b.substring(0,b.length()-1),DP) + cost;
        if(distance3 < min){
            min = distance3;
        }

        DP[i][j] = min;
        return min;
    }

    public static int minEditDist(String a, String b) {
        // TODO: Your code here
        int[][] DP = new int[a.length() + 1][b.length() + 1];
        for(int i = 0; i <= a.length(); i++){
            for(int j = 0; j <= b.length();j++){
                DP[i][j] = -1;
            }
        }

        return LevenshteinDistance(a,b,DP);
    }

}
