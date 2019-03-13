package range_finding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.left_subnodes = 1+count_subnodes(n.leftChild);
            n.right_subnodes = 1+ count_subnodes(n.rightChild);
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.left_subnodes = 1+count_subnodes(n.leftChild);
            n.right_subnodes = 1+ count_subnodes(n.rightChild);
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.left_subnodes = 1+count_subnodes(n.leftChild);
            n.right_subnodes = 1+ count_subnodes(n.rightChild);
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    private int count_subnodes(RangeNode x){
        if (x == null) return -1;
        else{
            return x.left_subnodes + x.right_subnodes;
        }
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(log(N) + L)
    public List<Integer> rangeIndex(int lo, int hi) {
        // TODO
        List<Integer> l = new LinkedList<>();
        int count = rangeCount(lo,hi);
        RangeIndexTraverse(root,l,lo,count);
        return l;
    }

    public int RangeIndexTraverse(RangeNode<Integer> node, List<Integer> list, int lo, int count) {
        if (node != null && count > 0) {
            if(node.key.compareTo(lo) >= 0) {
                count = RangeIndexTraverse(node.leftChild, list,lo,count);
                if(count > 0){
                    list.add(node.key);
                    count--;
                    count = RangeIndexTraverse(node.rightChild,list,lo,count);
                }
            }
            else{
                count = RangeIndexTraverse(node.rightChild,list,lo,count);
            }
        }
        return count;
    }

//    // Return all keys that are between [lo, hi] (inclusive).
//    // TODO: runtime = O(N)
//    public List<Integer> rangeIndex(int lo, int hi) {
//        // TODO
////        int count = rangeCount(lo,hi);
//        List<Integer> l = new LinkedList<>();
//
//        int count = 0;
//        List<Integer> sorted_node = inOrderTraversal();
//        for(int i = 0; i<sorted_node.size();i++){
//            if (sorted_node.get(i) <=hi && sorted_node.get(i) >= lo){
//                count++;
//                l.add(sorted_node.get(i));
//            }
//            else if(sorted_node.get(i) < lo){
//                continue;
//            }
//            else{
//                break;
//            }
//        }
//
//        return l;
//    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(log(N))
    public int rangeCount(int lo, int hi) {
        // TODO
        int count1 = rank(lo-1);
        int count2 = rank(hi);
        return count2 - count1;
    }

    public int rank(int k){
        int count = 0;
        RangeNode<Integer> current = root;
        while(current!=null){
            if(current.key.compareTo(k) <= 0){ // <= k
                count += current.left_subnodes + 1;
                current = current.rightChild;
            }
            else{
                current = current.leftChild;
            }
        }
        return count;
    }

//    public int rank(int k){ // O(N) method
//       int count = 0;
//       List<Integer> sorted_node = inOrderTraversal();
//       for(int i = 0; i<sorted_node.size();i++){
//           if (sorted_node.get(i) <=k){
//               count++;
//           }
//           else{
//               break;
//           }
//       }
//       return count;
//    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.left_subnodes = 1+count_subnodes(x.leftChild);
        x.right_subnodes = 1+ count_subnodes(x.rightChild);
        y.left_subnodes = 1+count_subnodes(y.leftChild);
        y.right_subnodes = 1+ count_subnodes(y.rightChild);
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.left_subnodes = 1+count_subnodes(x.leftChild);
        x.right_subnodes = 1+ count_subnodes(x.rightChild);
        y.left_subnodes = 1+count_subnodes(y.leftChild);
        y.right_subnodes = 1+ count_subnodes(y.rightChild);
        return y;
    }
}
