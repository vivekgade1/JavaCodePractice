package SPOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        int[] list = {30,8,52,3,20,10,29};
        BalancedBinaryTree givenTree = new BalancedBinaryTree(list);
        String line;
        while ((line = in.readLine()) != null) {
            String[] nums = line.split(" ");
            ArrayList path_way_1 = pathToNode(givenTree.root,Integer.parseInt(nums[0]),new ArrayList<Integer>());
            ArrayList path_way_2 = pathToNode(givenTree.root,Integer.parseInt(nums[1]),new ArrayList<Integer>());
            int iterator = path_way_1.size() > path_way_2.size()? path_way_2.size() : path_way_1.size();
            int result = givenTree.root.val;
            for (int i = 0; i < iterator; i++) {
                if(path_way_1.get(i) == path_way_2.get(i)){
                    if(result > (int) path_way_1.get(i)){
                        result = (int) path_way_1.get(i);
                    }
                }
            }
            System.out.println(result);
        }
    }


    public static ArrayList pathToNode(TreeNode node, int target, ArrayList<Integer>path){
        if(node.val == target){
            path.add(node.val);
            return path;
        }
        if(node.val > target){
            path.add(node.val);
            return pathToNode(node.left,target,path);
        }else{
            path.add(node.val);
            return pathToNode(node.right,target,path);
        }

    }

    public static class TreeNode{
        public TreeNode left = null;
        public TreeNode right = null;
        public int val;

        public TreeNode(int item) {
            this.val = item;
        }
    }

    public static class BalancedBinaryTree {
        public TreeNode root;

        public BalancedBinaryTree(int[] nums) {
            root = new TreeNode(nums[0]);
            root.val = nums[0];
            for (int i = 1; i < nums.length; i++) {
                addElement(nums[i]);
            }
        }

        public void addElement(int item) {
            TreeNode ele = root;
            TreeNode parent = root;
            while (ele != null) {
                parent = ele;
                if (item < ele.val) {
                    ele = ele.left;
                } else {
                    ele = ele.right;
                }
            }
            if (item < parent.val) {
                parent.left = new TreeNode(item);
            } else {
                parent.right = new TreeNode(item);
            }
        }
    }
    public static void navCalculator(String input){
        //"PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20"
        HashMap<String,HashMap<String,Float[]>> mapping = new HashMap<>();
        String[] port_bench = input.split("\\|");
        String[] port_details = port_bench[0].substring(5,port_bench[0].length()).split(";");
        String[] bench_details = port_bench[1].substring(6,port_bench[1].length()).split(";");
        mapping.put("Port",new HashMap<String,Float[]>());
        mapping.put("Bench",new HashMap<String,Float[]>());
        ArrayList<String> stock_list = new ArrayList<>();
        for (String str: port_details) {
            Float[] values = new Float[2];
            String[] list = str.split(",");
            values[0] = Float.parseFloat(list[1]);
            values[1] = Float.parseFloat(list[2]);
            mapping.get("Port").put(list[0],values);
            stock_list.add(list[0]);
        }

        for (String str: bench_details) {
            Float[] values = new Float[2];
            String[] list = str.split(",");
            values[0] = Float.parseFloat(list[1]);
            values[1] = Float.parseFloat(list[2]);
            mapping.get("Bench").put(list[0],values);
            if(!stock_list.contains(list[0])){
                stock_list.add(list[0]);
            }
        }

        float nav_port = calculateNav(mapping.get("Port"));
        float nav_bench = calculateNav(mapping.get("Bench"));
        ArrayList<String> result = new ArrayList<>();
        for (Object stk: stock_list) {
            Float [] port_values = mapping.get("Port").get(stk);
            Float [] bench_values = mapping.get("Bench").get(stk);
            float nav_percent_port = 0;
            float nav_percent_ben = 0;
            if(port_values!=null){
                nav_percent_port =  port_values[0]* port_values[1] * 100 / nav_port ;
            }
            if(bench_values != null) {
                nav_percent_ben=  bench_values[0]* bench_values[1] * 100 / nav_bench ;
            }
            float nav_diff = (float) (Math.round((nav_percent_port - nav_percent_ben)* 100.0) / 100.0);
            if(nav_diff == 0) nav_diff = -nav_diff;
            result.add(stk + ":" + String.format( "%.2f",nav_diff));
        }
        System.out.println(result.toString().substring(1,result.toString().length()-1).replace(", ",","));
    }


    private static float calculateNav(HashMap<String, Float[]> stocks) {
        float nav = 0;
        for (String stk: stocks.keySet()) {
             nav += stocks.get(stk)[0] * stocks.get(stk)[1];
        }
        return nav;
    }

    public static String calInterest(String[] nums){
        float principal = Float.parseFloat(nums[0]);
        float downpayement = Float.parseFloat(nums[3]);
        float loan_amt = principal-downpayement;
        float rate = (float) (((float)(Float.parseFloat(nums[2])/ 100.0))/12.0);
        int n = Integer.parseInt(nums[1])*12;
        //DecimalFormat df = new DecimalFormat("#.00");
        float result = (float) ((loan_amt*rate) /(1-Math.pow(1+rate,-n)));
        int interest = (int) (Math.round(result*n) - loan_amt);

        return("$" + String.format( "%.2f", result )  +"~" +"$"+interest);
        //String.format( "%.2f", result )
    }

    private static int continuousSubsequence(String[] nums) {
        int[] input = new int[nums.length];
        Queue<Integer> sums = new LinkedList<>();
        int result = 0;
        int size = nums.length;
        int present_size = 0;
        for (int i = 0; i < size; i++) {
            present_size = sums.size();
            while (present_size >0){
                int ele = sums.remove();
                int next = ele + Integer.parseInt(nums[i].trim());
                sums.add(next);
                if(result <= next) result = next;
                present_size--;
            }
            sums.add(Integer.parseInt(nums[i].trim()));
        }
        return result;
    }

}
