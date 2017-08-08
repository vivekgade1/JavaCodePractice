package trees;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vgade on 8/3/17.
 */
public class BalancedBinaryTree {
    public TreeNode root ;
    public BalancedBinaryTree(int[] nums){
        root = new TreeNode(nums[0]);
        root.val = nums[0];
        for (int i = 1;i<nums.length;i++) {
            addElement(nums[i]);
        }

        System.out.println("insert complete");
    }

    public void printTree(){
        printElements(root);
    }

    public void printLeafOfTree(){
        printLeafElements(root);
    }

    public void addElement(int item) {
        TreeNode ele = root;
        TreeNode parent = root;
        while (ele != null){
            parent = ele;
            if(item < ele.val){
                ele = ele.left;
            }else{
                ele = ele.right;
            }
        }
        if(item < parent.val){
            parent.left = new TreeNode(item);
        }else{
            parent.right = new TreeNode(item);
        }
    }

    public void printElements(TreeNode n){
        if(n.left != null) printElements(n.left);
        System.out.println(n.val);
        if(n.right != null) printElements(n.right);
    }

    public void printLeafElements(TreeNode n){
        if(n.left == null && n.right == null) System.out.println(n.val);
        if(n.left != null) printLeafElements(n.left);
        if(n.right != null) printLeafElements(n.right);
    }

    public void elementsInEachlevel(){

        HashMap<Integer, ArrayList<TreeNode>> levelsTreeNodeMap = new HashMap<>();
        ArrayList<TreeNode> TreeNodesInLevel = new ArrayList<>();
        int level = 0;
        TreeNodesInLevel.add(root);
        levelsTreeNodeMap.put(level,TreeNodesInLevel);
        while (levelsTreeNodeMap.get(level).isEmpty()){
            printTreeNodes(levelsTreeNodeMap.get(level));
            TreeNodesInLevel = new ArrayList<>();
            for (TreeNode ele: levelsTreeNodeMap.get(level)) {
                if(ele.left != null) TreeNodesInLevel.add(ele.left);
                if(ele.right != null) TreeNodesInLevel.add(ele.right);
            }
            level ++;
            levelsTreeNodeMap.put(level,TreeNodesInLevel);
        }

    }

    private void printTreeNodes(ArrayList<TreeNode> TreeNodes) {
        for (TreeNode item: TreeNodes) {
            System.out.print(" " + item.val);
        }
        System.out.println(" ");
    }

    public void postOrder(TreeNode node){
        if(node.left!=null) postOrder(node.left);
        if(node.right!=null) postOrder(node.right);
        System.out.println(node.val);
    }

}
