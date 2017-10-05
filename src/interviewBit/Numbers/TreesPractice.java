package interviewBit.Numbers;

import trees.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by vgade on 8/9/17.
 */
public class TreesPractice {

    public static void main(String[] args){
        int[] list1 = new int[]{4,2,6,1,3,5,7};
        int[] list2 = new int[]{10,5,15,12,16,13};
        //BalancedBinaryTree sample1 = new BalancedBinaryTree(list1);
        BalancedBinaryTree sample2 = new BalancedBinaryTree(list2);
        System.out.println(minHeight(sample2.root));
    }

    /*-------------------------------------------------Compare two trees-----------------------------------------------------------------*/
    public static int isSameTree(TreeNode a, TreeNode b) {
        ArrayList<Integer> a_sorted = new ArrayList<>();
        ArrayList<Integer> b_sorted = new ArrayList<>();
        if(a == null && b == null){
            return 1;
        }else if(a== null || b == null){
            return 0;
        }
        inorderTraversal(a,a_sorted);
        inorderTraversal(b,b_sorted);
        if(a_sorted.size() == b_sorted.size()){
            for (int i = 0; i <  a_sorted.size(); i++) {
                if(Integer.compare(a_sorted.get(i),b_sorted.get(i)) != 0){
                    return 0;
                }
            }
            return 1;
        }else {
            return 0;
        }

    }

    public static void inorderTraversal(TreeNode node, ArrayList<Integer> list){
        if(node.left != null) inorderTraversal(node.left,list);
        list.add(node.val);
        if(node.right != null) inorderTraversal(node.right,list);
    }

    public static void preorderTraversal(TreeNode node, ArrayList<Integer> list){
        list.add(node.val);
        if(node.left != null) preorderTraversal(node.left,list);
        if(node.right != null) preorderTraversal(node.right,list);
    }
    /*-------------------------------------------------End Compare two trees-----------------------------------------------------------------*/

    /*-------------------------------------------------Traversals without recursions-----------------------------------------------------------------*/

    public static void inOrderTraversalNoREC(TreeNode root){
        Stack<TreeNode> list = new Stack<>();
        while(root != null){
            list.add(root);
            root = root.left;
        }

        while (!list.isEmpty()){
            TreeNode popped = list.pop();
            System.out.println(popped.val);
            if(popped.right != null){
                TreeNode ele = popped.right;
                while(ele != null){
                    list.add(ele);
                    ele = ele.left;
                }
            }

        }

    }
    public static void postOrderTraversalNoREC(TreeNode root){
        Stack<TreeNode> list = new Stack<>();
        while(root != null){
            list.add(root);
            root = root.left;
        }

        while (!list.isEmpty()){
            TreeNode popped = list.pop();
            if(popped.right != null){

                TreeNode ele = popped.right;
                popped.right = null;
                list.add(popped);
                while(ele != null){
                    list.add(ele);
                    ele = ele.left;
                }
                continue;
            }

            System.out.println(popped.val);
        }

    }

    public static ArrayList<Integer> preOrderTraversalNoREC(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> list = new Stack<>();
        int rootVal = root.val;
        while(root != null){
            list.add(root);
            result.add(root.val);
            root = root.left;
        }

        while (!list.isEmpty()){
            TreeNode popped = list.pop();
            if(popped.right != null){
                TreeNode ele = popped.right;
                while(ele != null){
                    list.add(ele);
                    result.add(ele.val);
                    ele = ele.left;
                }
            }
        }
        return result;
    }
    /*-------------------------------------------------End-----------------------------------------------------------------*/


    /*-------------------------------------------------End-----------------------------------------------------------------*/

    public TreeNode getSuccessor(TreeNode a, int b) {
        ArrayList<TreeNode> path = new ArrayList<>();
        inorderPathNodes(a,path);
        TreeNode result = new TreeNode(0);
        for (int i = path.size()-1; i >= 0 ; i--) {
            if(path.get(i).val == b){
                if(i != path.size()-1){
                    result = path.get(i+1);
                }else{
                    return null;
                }
            }
        }
        return result;

    }
    public static void inorderPathNodes(TreeNode node, ArrayList<TreeNode> list){
        if(node.left != null) inorderPathNodes(node.left,list);
        list.add(node);
        if(node.right != null) inorderPathNodes(node.right,list);
    }
    /*-------------------------------------------------End-----------------------------------------------------------------*/


    /*------------------------------------------------- kth smallest element in the BST-----------------------------------------------------------------*/
    public static int kthsmallest(TreeNode root, int k) {
        Stack<TreeNode> list = new Stack<>();

        while (root != null){
            list.add(root);
            root = root.left;
        }
        int count = 0;
        while (!list.isEmpty()){
            TreeNode popped = list.pop();
            count ++;
            if(count == k){
                return popped.val;
            }
            if(popped.right != null){
                TreeNode ele = popped.right;
                while (ele != null){
                    list.add(ele);
                    ele = ele.left;
                }
            }

        }
        return count;

    }
    /*-------------------------------------------------End kth smallest element in the BST-----------------------------------------------------------------*/

    /*-------------------------------------------------Zig Zag level printing-----------------------------------------------------------------*/


    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
        ArrayList<TreeNode> level_nodes = new ArrayList<>();
        level_nodes.add(a);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int level = 0;
        while (!level_nodes.isEmpty()){
            result.add(getLevelElements(level_nodes,level));
            ArrayList<TreeNode> next_nodes = new ArrayList<>();
            for (TreeNode item : level_nodes) {
                if(item.left != null) next_nodes.add(item.left);
                if(item.right != null) next_nodes.add(item.right);
            }
            level_nodes.clear();
            level_nodes = next_nodes;
            level++;
        }

        return result;


    }

    private static ArrayList<Integer> getLevelElements(ArrayList<TreeNode> level_nodes, int level) {
        ArrayList<Integer> list = new ArrayList<>();
        if(level%2 == 0){
            for (int i = 0; i < level_nodes.size(); i++) {
                list.add(level_nodes.get(i).val);
            }
        }else{
            for (int i = level_nodes.size() -1; i >= 0; i--) {
                list.add(level_nodes.get(i).val);

            }
        }
        return list;
    }

    /*-------------------------------------------------End Zig Zag level printing-----------------------------------------------------------------*/


    public static int isBalanced(TreeNode a) {
        if(a == null) return 1;
        int left_depth = checkDepth(a.left);
        int right_depth = checkDepth(a.right);
        if(left_depth == right_depth){
            return 1;
        }else {
            return 0;
        }

    }

    private static int checkDepth(TreeNode root){
        Stack<TreeNode> list = new Stack<>();
        int max_depth = 0, depth = 0;
        while (root != null){
            max_depth++;
            depth ++;
            list.add(root);
            root = root.left;
        }

        while (!list.isEmpty()){
            TreeNode popped = list.pop();
            if(popped.right != null){
                TreeNode ele = popped.right;
                while (ele !=null){
                    list.add(ele);
                    depth++;
                    ele = ele.left;
                    if(depth > max_depth) max_depth = depth;
                }
            }else{
                depth --;
            }
        }

        return max_depth;
    }
    // with recursion
    private static int maxDepth(TreeNode node){
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }


    // minimum depth of the tree
    /*Here i am going level by level and check for leaf node. If I find one i add up the level +1 and return*/
    public static int minHeight(TreeNode root){
        int level = 0;
        ArrayList<TreeNode> level_list = new ArrayList<>();
        ArrayList<TreeNode> next_level_list = new ArrayList<>();
        if(root.left == null && root.right == null) {
            return 1;
        }
        level_list.add(root);

        while (!level_list.isEmpty()){
            for (TreeNode node: level_list) {
                if(node.left == null && node.right == null) return level +1;
                else {
                    if (node.left != null) next_level_list.add(node.left);
                    if (node.right != null) next_level_list.add(node.right);
                }
            }
            level_list = (ArrayList<TreeNode>) next_level_list.clone();
            next_level_list.clear();
            level++;
        }
        return level +1;
    }



}
