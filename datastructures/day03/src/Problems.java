import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        int subtract = k;
        List<Integer> l = new LinkedList<>();
        boolean[] trash = new boolean[A.length];
        for (int i=0; i<A.length; i++) {
            trash[i] = Boolean.TRUE;
        }
        int cur_index = 0;
        int next_index = 1;
        while (k > 0 && next_index < A.length) {
            if (A[cur_index] <= A[next_index]) {
                cur_index += 1;
                next_index += 1;
            } else {
                trash[cur_index] = Boolean.FALSE;
                cur_index += 1;
                next_index = cur_index - 2;
                k -= 1;
                while (next_index >= 0 && k > 0) {
                    if (A[cur_index] >= A[next_index]) {
                        next_index -= 1;
                    } else if (A[cur_index] < A[next_index] && trash[next_index]) {
                        trash[next_index] = Boolean.FALSE;
                        k -= 1;
                        next_index -= 1;
                    }
                    else{
                        next_index -= 1;
                    }
                }
                next_index = cur_index + 1;
            }
        }
        int counter = 0;
        int index_counter = 0;
        while(counter < A.length - subtract){
            if (trash[index_counter]){
                l.add(A[index_counter]);
                counter++;
            }
            index_counter ++;
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        Node slow_ptr = n;
        Node fast_ptr = n;
        Node prev_slow_ptr = null;
        if (n != null && n.next != null){
            while (fast_ptr != null && fast_ptr.next != null){
                prev_slow_ptr = slow_ptr;
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next; // end on mid term if odd elements
            }

            prev_slow_ptr.next = null; // terminate first half

            if (fast_ptr != null){
                slow_ptr = slow_ptr.next; // slow ptr shifts from mid point to next
            }

            // reverse the second half
            Node curr_node = slow_ptr;
            Node prev_node = null;
            while (curr_node != null){
                Node temp = curr_node.next;
                curr_node.next = prev_node;
                prev_node = curr_node;
                curr_node = temp;
            }

            // compare first half and second half
            Node compare_node1 = n;
            Node compare_node2 = prev_node;
            while (compare_node1 != null && compare_node2 != null){
                if (compare_node1.val == compare_node2.val){
                    compare_node1 = compare_node1.next;
                    compare_node2 = compare_node2.next;
                }
                else {
                    return false;
                }
            }
            return true;

        }
        else{
            return  true;
        }
    }

    public static String infixToPostfix(String s) {
        ArrayList<Character> Postfix = new ArrayList<>();
        Stack<Character> operands = new Stack<>();
        for (int i = 0; i < s.length();i++){
            char val = s.charAt(i);
            if (Character.isDigit(val)){
                Postfix.add(val);
            }
            else if(CheckOperand(val) > 0){
                operands.push(val);
            }
            else if(val == ')'){
                char oprd = operands.pop();
                Postfix.add(oprd);
            }
        }
        StringBuilder builder = new StringBuilder(Postfix.size());
        for (char ch: Postfix){
            builder.append(ch);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    public static  int CheckOperand(char val){
        switch (val){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

}
