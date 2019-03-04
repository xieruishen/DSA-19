import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        Collections.sort(values);
        TreeNode<Integer> root = SortedArrayToBST(values, 0, values.size()-1);
        BinarySearchTree<Integer> result = new BinarySearchTree<>();
        result.root = root;
        for(int i:traverse_preorder(root)){
            result.add(i);
        }
        return result;
    }

    public static TreeNode<Integer> SortedArrayToBST(List<Integer> arr, int start, int end) {

        /* Base Case */
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode<Integer> node = new TreeNode<>(arr.get(mid));
        node.leftChild = SortedArrayToBST(arr, start, mid - 1);
        node.rightChild = SortedArrayToBST(arr, mid + 1, end);

        return node;
    }
    public static ArrayList<Integer> traverse_preorder(TreeNode<Integer> node){
        ArrayList<Integer> result = new ArrayList<>();
        if (node == null){
            return result;
        }
        result.add(node.key);
        result = traverse_preorder(node.leftChild);
        result.addAll(traverse_preorder(node.rightChild));
        return result;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
