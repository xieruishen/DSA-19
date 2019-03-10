public class TreeNode<T extends Comparable>{
    T key;
    TreeNode<T> leftChild, rightChild;
    boolean color;

    public TreeNode(T key) {
        this(key, RedBlackTree.RED);
    } // make a red node
    public TreeNode(T key, boolean color){ // make a black node
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
        this.color = color;
    }
}