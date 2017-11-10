package interviewBit.Numbers;

import java.util.*;

public class BackTracking {
    public static void main(String[] args){
        int[] a = { 15, 8, 15, 10, 19, 18, 10, 3, 11, 7, 17};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: a) {
            list.add(i);
        }

        System.out.println(combinationSum(list,33));
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
    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
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
}
