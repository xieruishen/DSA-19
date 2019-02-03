package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> tl;

    public MyStack() {
        ll = new LinkedList<>();
        tl = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (tl.isEmpty()){
            tl.addFirst(e);
        }
        else{
            int tl_top = tl.getFirst();
            if (e > tl_top){
                tl.addFirst(e);
            }
            else{
                tl.addFirst(tl_top);
            }
        }

    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        Integer poptl = tl.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() { return ll.isEmpty(); }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return tl.getFirst();
    }
}
