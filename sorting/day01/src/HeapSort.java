public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        // TODO
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);

        // If left child is larger than root
        if (l < size && heap[l] > heap[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < size && heap[r] > heap[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            sink(largest);
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            // TODO
            sink(i);

        }
    }

    /**
     * Best-case runtime: O(Nlgn)
     * Worst-case runtime:O(Nlgn)
     * Average-case runtime: O(Nlgn)
     *
     * Space-complexity:O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            // TODO
            int temp = heap[i];
            heap[i] = heap[0];
            heap[0] = temp;
            size --;
            sink(0);
        }

        return heap;
    }
}
