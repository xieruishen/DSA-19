import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        char[] clock = new char[hoursInDay];
        coinsOnAClockHelper(pennies,nickels,dimes,0,clock,result);
        return result;
    }

    public static void coinsOnAClockHelper(int pennies,int nickels,int dimes, int time, char[] clock, List<char[]> result){
        if(pennies + nickels + dimes == 0){
            char[] sequence = new char[clock.length];
            System.arraycopy(clock,0,sequence,0,clock.length);
            result.add(sequence);
            return;
        }
        if(clock[time] == '\u0000'){
            if(pennies > 0){
                clock[time] = 'p';
                int next_time = (time + 1) % clock.length;
                coinsOnAClockHelper(pennies-1,nickels,dimes,next_time,clock,result);
                clock[time] = '\u0000';
            }
            if(nickels > 0){
                clock[time] = 'n';
                int next_time = (time + 5) % clock.length;
                coinsOnAClockHelper(pennies,nickels-1,dimes,next_time,clock,result);
                clock[time] = '\u0000';
            }
            if(dimes > 0){
                clock[time] = 'd';
                int next_time = (time + 10) % clock.length;
                coinsOnAClockHelper(pennies,nickels,dimes-1,next_time,clock,result);
                clock[time] = '\u0000';
            }
        }

        }

    }
