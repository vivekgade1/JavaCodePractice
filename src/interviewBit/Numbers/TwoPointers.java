package interviewBit.Numbers;

import java.util.*;

/**
 * Created by vgade on 7/27/17.
 */
public class TwoPointers{
    public static void main(String [] args){
        Integer[] list1 = {-5, 1, 4, -7, 10, -7, 0, 7, 3, 0, -2, -5, -3, -6, 4, -7, -8, 0, 4, 9, 4, 1, -8, -6, -6, 0, -9, 5, 3, -9, -5, -9, 6, 3, 8, -10, 1, -2, 2, 1, -9, 2, -3, 9, 9, -10, 0, -9, -2, 7, 0, -4, -3, 1, 6, -3};
        //Integer[] list2 = {-4,-3,-2,-2};
        ArrayList<Integer> a_list1 = new ArrayList<>();
        //ArrayList<Integer> a_list2 = new ArrayList<>();
        for (Integer i: list1) {
            a_list1.add(i);
        }
//        for (Integer i: list2) {
//            a_list2.add(i);
//        }

        System.out.println(threeSumClosest(a_list1,-1));
    }

    public static int threeSumClosest(ArrayList<Integer> a, int b) {
        Collections.sort(a);
        int result = Integer.MAX_VALUE;
        //int insert_pos = insertFromLast(a,b);
        for (int i = 0; i < a.size() ; i++) {
            for (int j = i+1; j < a.size()-1 ; j++) {
                int sum = a.get(i) + a.get(j) + a.get(j+1);
                if(Math.abs(sum) - Math.abs(b) < result){
                    result = Math.abs(sum);
                }
            }
        }

        return result;
    }

    public static int diffPossible(ArrayList<Integer> a, int b) {
        int search_val = 0,indx;
        for (int i = 0; i < a.size() ; i++) {
            search_val = a.get(i) + b;
            indx = binarySearch(a,search_val);
            if(indx!= -1 && indx != i){
                return 1;
            }
        }
        return 0;

    }

    public static int binarySearch(ArrayList<Integer> a ,int num){
        int low = 0;
        int high = a.size()-1;
        int mid=0, present_num = 0;
        while (low <=high){
            mid = (low + high)/2;
            present_num = a.get(mid);
            if(present_num == num){
                return mid;
            }
            if(present_num > num){
                high = mid -1;
            }else{
                low = mid +1;
            }

        }
        return -1;
    }

    /*----------------------------------------- Merge two sorted arrays in m+n ------------------------------------------------------*/

    public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        int size_a = a.size();
        int size_b = b.size();
        int p_a = 0,p_b = 0;
        while(p_a + p_b < size_a + size_b){
            if(p_a == size_a){
                a.add(b.get(p_b));
                p_b ++;

            }else if(p_b < size_b){
                if(a.get(0) < b.get(p_b)){
                    a.add(a.get(0));
                    a.remove(0);
                    p_a ++;

                }else if(a.get(0) == b.get(p_b)){
                    a.add(a.get(0));
                    a.remove(0);
                    a.add(b.get(p_b));
                    p_a ++;
                    p_b++;

                }else {
                    a.add(b.get(p_b));
                    p_b ++;
                }
            }else{
                a.add(a.get(0));
                a.remove(0);
                p_a ++;
            }
        }

    }

    /*----------------------------------------- end Merge two sorted arrays in m+n ------------------------------------------------------*/


    /**/

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> a) {
       int low = 0;
       int pointer;

       while (low< a.size()){
           pointer = low+1;
           while (pointer < a.size() && Integer.compare(a.get(low),a.get(pointer)) == 0){
               a.remove(pointer);
           }
           low ++;
        }

        return a;

    }

    /*------------------------------------------------- Intersection Of Sorted Arrays ------------------------------------------------------------------*/

    public static ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int l = 0,m = 0;
        while (l<a.size() && m < b.size()){
            if(a.get(l) < b.get(m)){
                l++;
            }else if(a.get(l) > b.get(m)){
                m++;
            }else{
                result.add(a.get(l));
                l++;
                m++;
            }
        }

        return result;

    }

    /*------------------------------------------------- End of Intersection Of Sorted Arrays ------------------------------------------------------------------*/

    /*------------------------------------------------- Remove element from an array------------------------------------------------------------------*/

    public static int removeElement(ArrayList<Integer> a, int b) {
        int low = 0;
        int high = a.size()-1;
        while (low <=high){
            int present = a.get(low);
            if(present != b){
                if(high != low && a.get(high) == b){
                    a.remove(high);
                }
                high--;
                low ++;
            }else {
                a.remove(low);
                high --;
            }

        }
        return a.size();
    }

    public static int insertFromLast(ArrayList list, int num){
        int low = 0;
        int high = list.size() -1;
        int present_val,mid;
        while (low <=high){
            mid = (low+high)/2;
            present_val = (int) list.get(mid);
            if(present_val > num){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }
    /*------------------------------------------------- End of Remove element from an array------------------------------------------------------------------*/

    /*--------------------------------------------------------Counting Triangles ------------------------------------------------------------------------------*/

    public int nTriang(List<Integer> a) {
        int size = a.size();
        Collections.sort(a);
        final int max = a.get(size-1);
        int result = 0;
        for (int i = 0; i < size ; i++) {
            for (int j = i+1; j < size-1  ; j++) {
                int sum = a.get(i) + a.get(j);
                if(sum > max) result += size-j-1;
                else{
                    result = result+ checkPosibility(a.subList(j+1,size-1),sum);
                }

            }

        }
        return result;

    }

    public int checkPosibility(List l,int num){
        if(l.size() == 0|| num < (int)l.get(0)){
            return 0;
        }else if(num > (int)l.get(l.size()-1)){
            return l.size();
        }
        int low = 0;
        int high = l.size() -1;
        int mid, present_num;
        while (low <= high){
            mid = (low + high)/2;
            present_num = (int)l.get(mid);
            if(present_num < num){
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return low;
    }


    /*--------------------------------------------------------End of Counting Triangles------------------------------------------------------------------------*/


    /*-----------------------------------------------------------Minimize absolute difference---------------------------------------------------------------------*/

    public static int solve(List<Integer> a, List<Integer> b, List<Integer> c) {
        int size_a = a.size();
        int size_b = b.size();
        int size_c = c.size();
        int i = size_a-1,j= size_b-1,k = size_c-1,index=0,result = Integer.MAX_VALUE;
        int[] nums = {a.get(i),b.get(j),c.get(k)};
        result = getDifference(nums);
        while (true){
            if(result == 0){
                return 0;
            }

            switch (getMax(nums)){
                case 0:
                    i--;
                    index = i;
                    break;
                case 1:
                    j--;
                    index = j;
                    break;
                case 2:
                    k--;
                    index = k;

                    break;
            }
            if(index == -1){
                break;
            }
            nums[0] = a.get(i);
            nums[1] = b.get(j);
            nums[2] = c.get(k);
            int difference = getDifference(nums);
            if(result > difference){
                result = getDifference(nums);
            }
        }
        return result;
    }

    public static int getDifference(int [] nums){
        int max_index = getMax(nums);
        int max = nums[max_index];
        int min = 0;
        switch (max_index){
            case 0:
                if(nums[1]> nums[2]) min = nums[2];
                else min = nums[1];
                break;
            case 1:
                if(nums[0]> nums[2]) min = nums[2];
                else min = nums[0];
                break;
            case 2:
                if(nums[0]> nums[1]) min = nums[1];
                else min = nums[0];
                break;
        }
        return max-min;
    }

    public static int getMax(int[] nums){
        if(nums[0] >= nums[1] && nums[0] >= nums[2]) return 0;
        else if(nums[1] >= nums[0] && nums[1] >= nums[2]) return 1;
        else return 2;
    }

    /*--------------------------------------------------------------------------------------------------------------------------------*/

}
