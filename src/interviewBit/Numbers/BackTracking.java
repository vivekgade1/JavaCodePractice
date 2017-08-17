package interviewBit.Numbers;

import java.util.Stack;

public class BackTracking {
    public static void main(String[] args){
        int[] a = {1,1,6,4,1};
        System.out.println(lPalin(createLinkedList(a)));
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
