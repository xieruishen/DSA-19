package range_finding;

public class RangeNode<T extends Comparable<T>> {
    public T key;
    public RangeNode<T> leftChild;
    public RangeNode<T> rightChild;
    public int height;
    public int left_subnodes;
    public int right_subnodes;

    public RangeNode(T key) {
        this(key, 0);
    }

    public RangeNode(T key, int height) {
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
        this.height = height;
        this.left_subnodes = 0;
        this.right_subnodes = 0;
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    public boolean isLeaf() {
        return !this.hasLeftChild() && !this.hasRightChild();
    }

    @Override
    public boolean equals(Object other) {
        RangeNode otherNode = (RangeNode) other;
        return otherNode.key.equals(this.key);
    }
}
