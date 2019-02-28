import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        Building[] sorted = sort(B);
        return convert_building_to_point(sorted);
    }
    public static Building[] sort(Building[] B){
        Building[] sorted = new Building[B.length];
        int l = 0;
        int r = B.length-1;
        if (l < r){
            int m = (int)Math.ceil((double)(l + r)/2);
            Building[] left = new Building[m];
            Building[] right = new Building[B.length-m];
            for (int i = 0; i < m; i++){
                left[i] = B[i];
            }
            for (int j = 0; j < B.length-m;j++){
                right[j] = B[m+j];
            }
            Building[] sortedl = sort(left);
            Building[] sortedr = sort(right);
            sorted = merge(sortedl,sortedr);
        }
        else {
            sorted = B;
        }
        return sorted;
    }

    public static Building[] merge(Building[] left, Building[] right){
        ArrayList<Building> result = new ArrayList<>();
        int i = 0; // index of first array
        int j = 0; // index of second array
        while (i < left.length && j < right.length){
            if(left[i].l<=right[j].l){
                if(left[i].r > right[j].r){
                    if (left[i].h >= right[j].h){
                        j++;
                    }
                    else{
                        if (left[i].l - right[j].l != 0){
                            Building temp1 = new Building(left[i].l, right[j].l, left[i].h);
                            result.add(temp1);
                            result.add(right[j]);
                            left[i].l = right[j].r;
                            j++;
                        }
                        else{
                            result.add(right[j]);
                            left[i].l = right[j].r;
                            j++;
                        }
                    }
                }
                else if(left[i].r == right[j].r){
                    if (left[i].h >= right[j].h){
                        j++;
                    }
                    else{
                        if (left[i].l - right[j].l != 0){
                            left[i].r = right[j].l;
                            result.add(left[i]);
                            result.add(right[j]);
                            i++;
                            j++;
                        }
                        else{
                            result.add(right[j]);
                            i++;
                            j++;
                        }
                    }
                }
                else{
                    if(left[i].r < right[j].l){
                        result.add(left[i]);
                        i++;
                    }
                    else{
                        if(left[i].h > right[j].h){
                            right[j].l = left[i].r;
                            result.add(left[i]);
                            i++;
                        }
                        else{
                            if(left[i].l - right[j].l != 0){
                                left[i].r = right[j].l;
                                result.add(left[i]);
                                i++;
                            }
                            else{
                                i++;
                            }
                        }
                    }
                }
            }
            else{
                if(right[j].r > left[i].r){
                    if (right[j].h >= left[i].h){
                        i++;
                    }
                    else{
                        if (right[j].l - left[i].l != 0){
                            Building temp1 = new Building(right[j].l, left[i].l, right[j].h);
                            result.add(temp1);
                            result.add(left[i]);
                            right[j].l = left[i].r;
                            i++;
                        }
                        else{
                            result.add(left[i]);
                            right[j].l = left[i].r;
                            i++;
                        }
                    }
                }
                else if(right[j].r == left[i].r){
                    if (right[j].h >= left[i].h){
                        i++;
                    }
                    else{
                        if (right[j].l - left[i].l != 0){
                            right[j].r = left[i].l;
                            result.add(right[j]);
                            result.add(left[i]);
                            i++;
                            j++;
                        }
                        else{
                            result.add(left[i]);
                            i++;
                            j++;
                        }
                    }
                }
                else{
                    if(right[j].r < left[i].l){
                        result.add(right[j]);
                        j++;
                    }
                    else{
                        if(right[j].h > left[i].h){
                            left[i].l = right[j].r;
                            result.add(right[j]);
                            j++;
                        }
                        else{
                            if(right[j].l - left[i].l != 0){
                                right[j].r = left[i].l;
                                result.add(right[j]);
                                j++;
                            }
                            else{
                                j++;
                            }
                        }
                    }
                }
            }
        }

        while (i < left.length){
            result.add(left[i]);
            i ++;
        }
        while (j < right.length){
            result.add(right[j]);
            j ++;
        }
        Building[] output = new Building[result.size()];
        for(int k = 0; k< result.size(); k++){
            output[k] = result.get(k);
        }
        return output;
    }

    public static List<Point> convert_building_to_point(Building[] sorted){
        ArrayList<Point> result= new ArrayList<>();
        Point p1 = new Point(sorted[0].l, sorted[0].h);
        result.add(p1);
        Building prev = sorted[0];
        for(int i = 1; i<sorted.length;i++){
            if(sorted[i].l == prev.r && sorted[i].h == prev.h){
                prev = sorted[i];
                continue;
            }
            if(sorted[i].l > prev.r){
                result.add(new Point(prev.r, 0)); // empty
            }
            result.add(new Point(sorted[i].l, sorted[i].h));
            prev = sorted[i];
        }
        result.add(new Point(prev.r,0));
        return result;
    }
}
