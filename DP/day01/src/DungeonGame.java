public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        // TODO: Your code here
        int num_row = map.length;
        int num_col = map[0].length;
        int[][] DP = new int[num_row][num_col];
        // base case: destination
        if(map[num_row-1][num_col-1] > 0){
            DP[num_row-1][num_col-1] = 1;
        }
        else{
            DP[num_row-1][num_col-1] = Math.abs(map[num_row-1][num_col-1]) + 1;
        }

        // Fill out last column
        for(int i = num_row - 2; i >=0; i--){
            DP[i][num_col-1] = Math.max(DP[i+1][num_col-1] - map[i][num_col-1],1);
        }

        // Fill last row
        for(int j = num_col-2; j >= 0 ;j--){
            DP[num_row-1][j] = Math.max(DP[num_row-1][j+1] - map[num_row-1][j],1);
        }

        // Fill the rest of the table
        for(int i = num_row - 2; i>=0; i--){
            for(int j = num_col - 2; j>= 0; j--){
                int min_point_exit = Math.min(DP[i+1][j],DP[i][j+1]);
                DP[i][j] = Math.max(min_point_exit - map[i][j],1);

            }
        }

        return DP[0][0];
    }

}


//class Room{
//    int min_health, net_health;
//
//    public Room(int min_health, int net_health){
//        this.min_health = min_health;
//        this.net_health = net_health;
//    }
//}
//
//public class DungeonGame2 {
//
//    public static int minInitialHealth(int[][] map) {
//        // TODO: Your code here
//        int num_row = map.length;
//        int num_col = map[0].length;
//        Room[][] DP = new Room[num_row][num_col];
//
//        // Build DP table
//        for(int i = 0; i < num_row; i++){
//            for(int j = 0; j < num_col; j++){
//                int min_parent = Integer.MAX_VALUE;
//                Room parent = new Room(0,0);
//
//                if(i - 1 >= 0){
//                    if(DP[i-1][j].min_health < min_parent){
//                        min_parent = DP[i-1][j].min_health;
//                        parent = DP[i-1][j];
//                    }
//                }
//                if(j-1 >= 0){
//                    if(DP[i][j-1].min_health < min_parent){
//                        min_parent = DP[i][j-1].min_health;
//                        parent = DP[i][j-1];
//                    }
//                }
//
//                // check (0,0) position
//                if(min_parent == Integer.MAX_VALUE){
//                    min_parent = 0;
//                }
//
//                int net = min_parent + parent.net_health + map[i][j];
//                if(net > 0){
//                    DP[i][j] = new Room(min_parent,parent.net_health+map[i][j]);
//                }
//                else{
//                    DP[i][j] = new Room(-net + min_parent+1, parent.net_health+map[i][j]);
//                }
//            }
//        }
//        return DP[num_row-1][num_col-1].min_health;
//    }
// }
