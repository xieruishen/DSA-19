public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        this.elems = new Cow[10];
        this.size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        this.elems = new Cow[capacity];
        this.size = 0;
    }

    // TODO: Runtime: O(1*)
    public void add(Cow c) {
        int lowerlimit = 2;
        if (size >= elems.length){
            Cow[] bigger = new Cow[elems.length*2];
            System.arraycopy(elems,0,bigger,0,elems.length);
            elems = bigger;
        }
        else if(size <= 0.25*elems.length && elems.length > lowerlimit){
            Cow[] smaller = new Cow[elems.length/2];
            System.arraycopy(elems,0,smaller,0,size);
            elems = smaller;
        }
        elems[size] = c;
        size ++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Cow element = get(index);
        for (int i=index; i<size-1;i++){
            elems[i] = elems[i+1];
        }
        size --;
        return element;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (index < 0 || index > size ){
            throw new IndexOutOfBoundsException();
        }
        // add the cow to increment count
        add(c);

        //shift the other cows
        for(int i=size-1; i>index;i--){
            elems[i] = elems[i-1];
        }
        // put new one in right place
        elems[index] = c;
    }
}