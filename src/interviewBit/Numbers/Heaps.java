package interviewBit.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import interviewBit.Numbers.StringProblems.*;
import interviewBit.Numbers.ListNode;

public class Heaps {

    public static void main(String[] args){
        int [] list = {3, 2, 4, 2};
        int [] list2 = {4, 3, 1, 2 };
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            array.add(list[i]);
        }
        for (int i = 0; i < list2.length; i++) {
            array2.add(list[i]);
        }
        System.out.println(solve(array,array2));

    }


    public static int nchoc(int t, ArrayList<Integer> bags) {
        int total = 0;
        int key,max_bag;
        int size = bags.size();
        for (int i = 0; i < t ; i++) {
            max_bag = Collections.max(bags);
            total += max_bag;
            key = bags.indexOf(max_bag);
            bags.set(key,(int)Math.floor(bags.get(key)/2));
        }

        return total;


    }


    public static int nheapchoc(int t, ArrayList<Integer> bags) {
        int total = 0;
        int key,max_bag;
        int size = bags.size();
        TreeMap<Integer, Integer> mapping = new TreeMap<Integer, Integer>();
        for (int item : bags) {
            mapping.put(item, 1);
        }
        for (int i = 0; i < t ; i++) {
            max_bag = mapping.lastKey();
            total += max_bag;
            mapping.remove(max_bag);
            mapping.put((int)Math.floor(max_bag/2),1);
        }

        return total;
    }

    /* Merge k sorted lists */
    public ListNode mergeKLists(ArrayList<ListNode> all_lists) {
        TreeMap<Integer,ArrayList<ListNode>> list_pointers = new TreeMap<>();
        ListNode result = null,head = null;
        for (ListNode item: all_lists) {
            ArrayList<ListNode> pointers = null;
            if(list_pointers.get(item.val) == null){
                pointers = new ArrayList<>();
                pointers.add(item);
            }else{
                pointers = list_pointers.get(item.val);
                pointers.add(item);
            }
            list_pointers.put(item.val,pointers);
        }
        while (!list_pointers.isEmpty()){
            int smallest_val = list_pointers.firstKey();
            ArrayList<ListNode>key_pointers = list_pointers.get(smallest_val);
            for (ListNode item: key_pointers) {
                if (result == null){
                    result = new ListNode(smallest_val);
                    head = result;
                }
                else{
                    result.next = new ListNode(smallest_val);
                    result = result.next;
                }
                ListNode next_in_list = item.next;
                if(next_in_list != null){
                    if(list_pointers.get(next_in_list.val) == null){
                        ArrayList<ListNode> key_list = new ArrayList<>();
                        key_list.add(next_in_list);
                        list_pointers.put(next_in_list.val,key_list);
                    }else{
                        ArrayList<ListNode> key_list = list_pointers.get(next_in_list.val);
                        key_list.add(next_in_list);
                    }
                }
            }
            list_pointers.remove(smallest_val);
        }
        return head;

    }

    /* Distinct number in a window */
    public static ArrayList<Integer> dNums(ArrayList<Integer> list, int k) {
        TreeMap<Integer,Integer> num_map  = new TreeMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < k ; i++) {
            if(num_map.get(list.get(i)) == null){
                num_map.put(list.get(i),1);
            }else{
                int occurrance = num_map.get(list.get(i));
                num_map.put(list.get(i),occurrance+1);
            }
        }
        result.add(num_map.size());
        for (int i = 1; i <= size - k; i++) {
            int new_num = list.get(i + k - 1);
            int removed_num = list.get(i - 1);
            if (num_map.get(removed_num) != null) {
                num_map.put(removed_num, num_map.get(removed_num) - 1);
                if (num_map.get(removed_num) == 0) {
                    num_map.remove(removed_num);
                }
            }

            if(num_map.get(new_num) == null){
                num_map.put(new_num,1);
            }else{
                num_map.put(new_num,num_map.get(new_num) +1);
            }
            result.add(num_map.size());
        }
        return result;

    }

    /*Maximum n element combinations*/
    public static ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int size = A.size();
        ArrayList<Integer> result = new ArrayList<>();
        int p1 = size-1;
        int p2 = size-1;
        boolean flag = true;

        while (result.size() < size){
            if (flag) {
                for (int i = size-1; i >= p2 ; i--) {
                    result.add(A.get(p1) + A.get(i));

                }
                p1--;
                flag = false;
            } else {
                for (int i = size - 1; i >= p1; i--) {
                    if(p2 != size-1 && i != size-1){
                        result.add(A.get(i) + A.get(p2));

                    }
                }
                p2--;
                flag = true;
            }
        }

        return result ;
    }

}
