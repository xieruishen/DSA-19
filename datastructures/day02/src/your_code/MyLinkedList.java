package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size != 0){
            Node n1 = new Node(c,tail,null);
            tail.next = n1;
            tail = n1;
        }
        else {
            Node n1 = new Node(c);
            tail = n1;
            head = n1;
        }
        size ++;
    }

    public void addFirst(Chicken c) {
        if (size != 0){
            Node n1 = new Node(c, null, head);
            head.prev = n1;
            head = n1;
        }
        else{
            Node n1 = new Node(c);
            head = n1;
            tail = n1;
        }
        size ++;
    }

    public Chicken get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i=0;i<index; i++){
            node = node.next;
        }
        return node.val;
    }

    public Chicken remove(int index) {
        Chicken bad;
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0){
            bad = removeFirst();
        }
        else if (index == size - 1){
            bad = removeLast();
        }
        else{
            Node escapes = head;
            for (int i=0;i<index; i++){
                escapes = escapes.next;
                escapes.prev.next = escapes.next;
                escapes.next.prev = escapes.prev;
            }
            size --;
            bad = escapes.val;
        }
        return bad;
    }

    public Chicken removeFirst() {
        Node escapes;
        if (size == 0){
            return null;
        }
        else if(size == 1){
            escapes = head;
            head = null;
            tail = null;
        }
        else{
            escapes = head;
            head = escapes.next;
            head.prev = null;
        }
        size --;
        return escapes.val;
    }

    public Chicken removeLast() {
        Node escapes;
        if (size == 0){
            return null;
        }
        else if(size == 1){
            escapes = tail;
            head = null;
            tail = null;
        }
        else{
            escapes = tail;
            tail = escapes.prev;
            tail.next = null;
        }
        size --;
        return escapes.val;
    }
}