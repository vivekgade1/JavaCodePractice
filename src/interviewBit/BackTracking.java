package interviewBit;

import java.util.*;

public class BackTracking {
    public static void main(String[] args){
        int[] a = { 1,2,3,4};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: a) {
            list.add(i);
        }

        formParanthesis(3);
    }


    // All permutations.
    public static int[][] permute(int[] nums) {
        int size = nums.length;
        int[][]result = new int[size][size];
        ArrayList<ArrayList<Integer>> orders = new ArrayList<>();

        numPermutation(nums,orders,new ArrayList<Integer>());
        return result;
    }

    private static void numPermutation(int[] nums, ArrayList<ArrayList<Integer>> orders,ArrayList current) {

        if(current.size() == nums.length){
            if(!orders.contains(current))
                orders.add(new ArrayList<Integer>(current));

            return;
        }
        for (int j = 0; j < nums.length ; j++) {
            if(current.contains(nums[j])) continue;
            current.add(nums[j]);
            numPermutation(nums,orders,current);
            current.remove(current.size()-1);
        }

    }

    private static int numSetBits(long a) {
        long remainder = a;
        int ones = 0;
        while (remainder>2){
            int r = (int) (remainder%2);
            if(r == 1){
                ones++;
            }
            remainder = remainder/2;
        }
        if(remainder ==1) ones++;
        return ones;

    }

    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> sub_result = new ArrayList<>();
        ArrayList<Integer> valid = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < a.size();i++) {
            int ele = a.get(i);
            if(i!=0  && ele == a.get(i-1)) {
                continue;
            }
            if(ele <= b){
                valid.clear();
                valid.add(ele);
                list.add(ele);
                result.add((ArrayList<Integer>) valid.clone());
                if(ele == b) count++;
            }else if(ele > b){
                break;
            }
        }

        while (count != result.size()|| result.size() ==0){
            count = 0;
            sub_result.clear();
            for (ArrayList arr: result) {
                int last_indx = list.indexOf(arr.get(arr.size()-1));
                int sum = getSum(arr);
                if(sum < b){
                    for (int j = last_indx; j < list.size() ; j++) {
                        valid.clear();
                        int ele = list.get(j);
                        if(sum + ele <= b){
                            valid.addAll(arr);
                            valid.add(ele);
                            sub_result.add((ArrayList<Integer>) valid.clone());
                            if(sum + ele == b) break;
                        }else{
                            break;
                        }
                    }
                }else if(sum == b){
                    sub_result.add(arr);
                    count++;
                }
            }

            if(sub_result.size() > 0){
                result = (ArrayList<ArrayList<Integer>>) sub_result.clone();
            }else{
                result.clear();
                break;
            }
        }


        return result;
    }

    private static int getSum(ArrayList<Integer> arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        return total;
    }

    /*
    * Combinations possible of certain size
    * */
    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n < k ) return result;
        for (int i = 1; i <=n; i++) {
            ArrayList<Integer> sub_arr = new ArrayList<Integer>();
            sub_arr.add(i);
            result.add(sub_arr);
            list.add(i);
        }
        int count = 1;
        
        while (count <k){
            ArrayList<ArrayList<Integer>> sub_result = new ArrayList<>();
            for (ArrayList arr: result) {
                int last_num = (int) arr.get(arr.size()-1);
                for (int i = last_num; i < n ; i++) {
                    ArrayList<Integer> sub_arr = new ArrayList<>(arr);
                    sub_arr.add(list.get(i));
                    sub_result.add(sub_arr);
                }
            }
            result = sub_result;
            count++;
        }
        return result;
    }

    /* Sub sets */
    public static ArrayList<ArrayList<Integer>> subsetsNormal(ArrayList<Integer> a) {
        Collections.sort(a);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int item :a){
            ArrayList<ArrayList<Integer>> interm_list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                ArrayList prev_list = new ArrayList<>(result.get(i));
                prev_list.add(item);
                interm_list.add(prev_list);
            }
            result.addAll(interm_list);
        }
        return result;
    }
    // Editorial solution for subsets lexical
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        output.add(new ArrayList<Integer>());
        if (a.size() == 0)
            return output;
        Collections.sort(a);
        generate(a, output, new ArrayList<Integer>(), 0);
        return output;
    }

    public void generate(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> output, ArrayList<Integer> temp, int index)
    {
        for (int i = index; i < a.size(); i++)
        {
            temp.add(a.get(i));
            output.add(new ArrayList<Integer>(temp));
            generate(a, output, temp, i+1);
            temp.remove(temp.size() - 1);
        }
    }



    /*-----------------------------------------------Palindrome list--------------------------------------------------------------------*/
    public static int lPalin(ListNode A) {
        Stack<Integer> reverse = new Stack<>();
        ListNode ele = A;
        while (ele != null){
            reverse.add(ele.val);
            ele = ele.next;
        }

        while (!reverse.isEmpty()){
            int num = reverse.pop();
            if(num != A.val){
                return 0;
            }
            A = A.next;
        }
        return 1;

    }

    /*-----------------------------------------------End of Palindrome list--------------------------------------------------------------------*/

    public static ListNode reverseRecursionList(ListNode a) {
        if(a.next==null)
        {
            return a;
        }
        ListNode head = reverseRecursionList(a.next);
        a.next.next = a;
        a.next = null;
        return head;
    }

    public static ListNode reverseList(ListNode a) {
        ListNode dummy = null;
        if(a.next == null) return a;
        while (a.next != null){
            ListNode next = a.next;
            a.next = dummy;
            dummy = a;
            a = next;
        }

        return dummy;
    }

    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        if(a == null || b == null) return null;
        int size_a = getListSize(a);
        int size_b = getListSize(b);
        if(size_a == 0 || size_b ==0) return null;
        if(size_a > size_b){
            for (int i = 0; i < size_a-size_b ; i++) {
                a = a.next;
            }
        }else if(size_a < size_b){
            for (int i = 0; i < size_b-size_a ; i++) {
                b = b.next;
            }
        }

        while ((a != null || b!= null) && !a.equals(b)){
            a = a.next;
            b = b.next;
        }
        if( a != null && a.equals(b)) return a;
        else return null;

    }

    public int getListSize(ListNode node){
        int size = 0;
        while (node.next != null){
            size++;
            node = node.next;
        }
        return size;

    }

    public static ListNode createLinkedList(int[] l){
        ListNode head = new ListNode(l[0]);
        ListNode presentNode = head;
        for (int i = 1; i < l.length ; i++) {
            ListNode ele = new ListNode(l[i]);
            presentNode.next = ele;
            presentNode  = ele;
        }
        return head;
    }

    public static String[] letterCombinations(String A) {
        HashMap<String,String> tele_map = new HashMap<>();
        tele_map.put("1","1");
        tele_map.put("0","0");
        tele_map.put("2","abc");
        tele_map.put("3","def");
        tele_map.put("4","ghi");
        tele_map.put("5","jkl");
        tele_map.put("6","mno");
        tele_map.put("7","pqrs");
        tele_map.put("8","tuv");
        tele_map.put("9","wxyz");
        ArrayList<String>output = new ArrayList<>();
        int size = A.length();

        ArrayList<String> mapping = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mapping.add(tele_map.get(A.substring(i,i+1)));
        }
        StringBuilder temp = new StringBuilder();
        getPermutations(output,mapping,temp,size,0);

        String[] result = new String[output.size()];
        for (int i = 0; i < result.length ; i++) {
            result[i] = output.get(i);
        }
        return  result;
    }

    private static void getPermutations(ArrayList<String> output, ArrayList<String> mapping, StringBuilder temp,int size, int indx) {
        String num_str = mapping.get(indx);
        if(indx < size){
            for (int i = 0; i < num_str.length(); i++) {
                temp.append(num_str.charAt(i));
                if(indx != size -1){
                    getPermutations(output,mapping,temp,size,indx+1);
                }
                if(temp.length() == size){
                    output.add(temp.toString());
                }
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }

    /* Generate all the Paranthesis.*/
    private static void formParanthesis(int n){
        String[] brackets = new String[]{"(",")"};
        List<String> output = new ArrayList<>();
        getValidParams(output,n,0,0,new StringBuilder(),brackets);

        String[] result = new String[output.size()];
        for (int i = 0; i < output.size(); i++) {
            result[i] = output.get(i);
        }
        System.out.println(output.toString());

    }

    private static void getValidParams(List<String> output, int n, int left,int right,StringBuilder inter_str,String[] brackets) {

        if(left == right && left == n){
            output.add(inter_str.toString());
        }else{
            if(left == right && left < n){
                inter_str.append(brackets[0]);
                getValidParams(output,n,left +1, right, inter_str,brackets);
            }else if(left > right && left < n){
                inter_str.append(brackets[0]);
                //String cur_str = inter_str.toString();
                String cur_str = inter_str.toString();
                getValidParams(output,n,left +1, right, inter_str,brackets);
                inter_str.setLength(0);
                inter_str.append(cur_str);
                inter_str.deleteCharAt(inter_str.length() -1);
                inter_str.append(brackets[1]);
                getValidParams(output,n,left, right + 1, inter_str,brackets);
            }else if(left > right && left == n){
                inter_str.append(brackets[1]);
                getValidParams(output,n,left, right + 1, inter_str,brackets);
            }
        }
    }


}
