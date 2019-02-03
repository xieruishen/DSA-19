package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private LinkedList<Integer> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

    public void enqueue(int item) {ll.add(item);}

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int max = ll.getFirst();
        int index = 0;
        for(int i = 1; i < ll.size();i++){
            int val = ll.get(i);
            if(val>max){
                max = val;
                index = i;
            }
        }
        ll.remove(index);
        return max;
    }

}