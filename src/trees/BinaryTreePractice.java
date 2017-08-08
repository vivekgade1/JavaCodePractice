package trees;

import interviewBit.Numbers.StringProblems;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by vgade on 8/3/17.
 */
public class BinaryTreePractice {
    public static void main(String[] args){
        int[] list = {9,4,3,2,45,10,10,1,17};
        BalancedBinaryTree sample = new BalancedBinaryTree(list);
        sample.printTree();
        System.out.println("-------------creation for the tree complete---------------");
        System.out.println(sumNumbers(sample.root));


    }

    public static void inorderTraversalNoREC(TreeNode root){
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
    public static void postTraversalNoREC(TreeNode root){
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

    public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        pathSum(root, sum, new ArrayList<>(), result);
        return result;
    }

    private static void pathSum(TreeNode root, int sum, ArrayList<Integer> currentResult, ArrayList<ArrayList<Integer>> result){
        if  (root == null){
            return;
        }

        if (root.left == null && root.right == null){
            if (root.val == sum){
                currentResult.add(root.val);
                result.add((ArrayList<Integer>) currentResult.clone());
                currentResult.remove(currentResult.size() - 1);
            }
            return ;
        }

        currentResult.add(root.val);
        sum -= root.val;
        pathSum(root.left, sum, currentResult, result);
        pathSum(root.right, sum, currentResult, result);
        currentResult.remove(currentResult.size() - 1);
    }


    public int maxDepth(TreeNode root) {
        int sum = 0;
        int[] max_depth = {0};
        depth(root,sum,max_depth);
        return max_depth[0];

    }

    public void depth(TreeNode root, int sum,int[] max_depth) {
        if(root == null){
            return;
        }
        sum+=1;
        if(root.left == null && root.right == null){
            if(sum > max_depth[0]){
                max_depth[0] = sum;
            }
        }
        depth(root.left,sum,max_depth);
        depth(root.right,sum,max_depth);
    }

    public int minDepth(TreeNode root) {
        int sum = 0;
        int[] min_depth = {Integer.MAX_VALUE};
        mdepth(root,sum,min_depth);
        return min_depth[0];

    }

    public void mdepth(TreeNode root, int sum,int[] min_depth) {
        if(root == null){
            return;
        }
        sum+=1;
        if(root.left == null && root.right == null){
            if(sum < min_depth[0]){
                min_depth[0] = sum;
            }
        }
        depth(root.left,sum,min_depth);
        depth(root.right,sum,min_depth);
    }

    public static int sumNumbers(TreeNode a) {
        ArrayList<> path_sums = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int total = 0;
        pathSum(a,path_sums,result);
        for (int i: path_sums) {

        }

        return total%1003;

    }

    private static void pathSum(TreeNode root, ArrayList<Integer> currentResult, ArrayList<ArrayList<Integer>> result){
        if  (root == null){
            return;
        }

        if (root.left == null && root.right == null){

                currentResult.add(root.val);
                result.add((ArrayList<Integer>) currentResult.clone());
                currentResult.remove(currentResult.size() - 1);

            return ;
        }

        currentResult.add(root.val);

        pathSum(root.left,currentResult, result);
        pathSum(root.right,currentResult, result);
        currentResult.remove(currentResult.size() - 1);
    }

}
