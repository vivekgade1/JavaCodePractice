package interviewBit;
import java.util.*;

public class LinkedListPrblms {

    public static void main(String[] args){
        int[] input = {1,1,1,1,2,2,2,3,3,4,5,6};
        ListNode h = new ListNode(input[0]);
        ListNode ele = h;
        for (int i = 1; i < input.length ; i++) {
            ele.next = new ListNode(input[i]);
            ele = ele.next;
        }
        System.out.println(deleteDuplicates(h));

    }

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        while(head != null && !(visited.contains(head))){
            visited.add(head);
            head = head.next;
        }
        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode left = head;
        ListNode right = head;
        while(right != null){
            while(right != null && left.val == right.val){
                right = right.next;
            }
            left.next = right;
            if(right != null)left = right;

        }
        return head;
    }
}
