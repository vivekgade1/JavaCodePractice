package interviewBit.Numbers;


import java.util.*;

/**
 * Created by vgade on 7/10/17.
 */
public class BinarySearch {
    public static void main(String [] args) {
        int[] list = {1,5,6,7,8};
        int[] list2 = {9,23,30,44,58,72,73,78};

        //System.out.println(findMedianSortedArrays(Arrays.asList(list),Arrays.asList(list2)));

    }




    /*------------------------ Start of Allocate books  ------------------------*/
    public static int books(List<Integer> a, int M) {
        Collections.sort(a);
        int max = getSum(a);
        int low = 0, high = a.size() -1;
        int pos,sum;
        int median = getSum(a)/2;
        while(M > 1){
            pos = findInsertPosition(a.subList(low,high),median);
            sum = getSum(a.subList(pos,high+1));
            high = pos;
            median = getSum(a.subList(low,high))/2;
            if(sum < max) max = sum;
            M--;
        }
        if(max < getSum(a.subList(low,high))) return getSum(a.subList(low,high));

        return max;
    }

    private static int getSum(List<Integer> a) {
        int sum = 0;
        for ( Integer i: a
                ) {
            sum += i;

        }
        return sum;
    }

    private static int getMean(List<Integer> a) {
        int sum = 0;
        for ( Integer i: a
             ) {
            sum += i;

        }
        return (int)Math.ceil(sum/a.size());
    }




    /*------------------------ End of Allocate books  ------------------------*/


    /*------------------------ Start of merge sorted lists  ------------------------*/
    public static double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        List<Integer> mergedList = new ArrayList<>();
        mergedList = mergeLists(a,b,mergedList);
        int total = mergedList.size();
        double median = 0;
        int m_pos = total/2;
        if(total % 2 == 0){
            median = (double)(mergedList.get(m_pos) + mergedList.get(m_pos -1))/2;
        }else{
            median = (double) mergedList.get(m_pos)/2;
        }
        return median;

    }
/*
    public static List<Integer> mergeLists(List<Integer> sub_a, List<Integer> sub_b,List<Integer> mergedList){

        int m = sub_a.size();
        int n = sub_b.size();
        int start_a = 0;
        int end_a = m;
        int start_b = 0;
        int end_b = n;

        int pos;

        while (mergedList.size() < m+n){
            if(m != 0 && n !=0){
                if(sub_a.get(start_a) > sub_b.get(start_b)){
                    pos = findInsertPosition(sub_b,sub_a.get(start_a));
                    mergedList.addAll(sub_b.subList(start_b,pos));
                    mergedList.add(sub_a.get(0));
                    mergeLists(sub_a.subList(1,sub_a.size()),sub_b.subList(pos,sub_b.size()),mergedList);
                }else{
                    pos = findInsertPosition(sub_a,sub_b.get(0));
                    mergedList.addAll(sub_a.subList(0,pos));
                    mergedList.add(sub_b.get(0));
                    mergeLists(sub_b.subList(1,sub_b.size()),sub_a.subList(pos,sub_a.size()),mergedList);
                }
            }

            if(m == 0 && n !=0){
                mergedList.addAll(sub_b);
            }else if(n == 0 && m != 0){
                mergedList.addAll(sub_a);
            }

        }
        return mergedList;
    }*/

    // This recursion is causing stack overflow exception.
    public static List<Integer> mergeLists(List<Integer> sub_a, List<Integer> sub_b,List<Integer> mergedList){

        int m = sub_a.size();
        int n = sub_b.size();
        int total = mergedList.size();
        int pos;
        if(m != 0 && n !=0){
            if(sub_a.get(0) > sub_b.get(0)){
                pos = findInsertPosition(sub_b,sub_a.get(0));
                mergedList.addAll(sub_b.subList(0,pos));
                mergedList.add(sub_a.get(0));
                mergeLists(sub_a.subList(1,sub_a.size()),sub_b.subList(pos,sub_b.size()),mergedList);
            }else{
                pos = findInsertPosition(sub_a,sub_b.get(0));
                mergedList.addAll(sub_a.subList(0,pos));
                mergedList.add(sub_b.get(0));
                mergeLists(sub_b.subList(1,sub_b.size()),sub_a.subList(pos,sub_a.size()),mergedList);
            }
        }

        if(m == 0 && n !=0){
            mergedList.addAll(sub_b);
        }else if(n == 0 && m != 0){
            mergedList.addAll(sub_a);
        }

        return mergedList;

    }

    private static int findInsertPosition(List<Integer> l, int num){
        if(num < l.get(0)){
            return 0;
        }else if(num > l.get(l.size()-1)){
            return l.size();
        }
        int low = 0;
        int high = l.size() -1;
        int mid, present_num;
        while (low <= high){
            mid = (low + high)/2;
            present_num = l.get(mid);
            if(present_num < num){
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return low;

    }


    /*------------------------ End of merge sorted lists  ------------------------*/


    /*------------------------ Start of the find minimum in a rotated array  ------------------------*/
    public static int findmin(final List<Integer> a){
        int low = 0;
        int high = a.size() -1;
        int left = a.get(low),right = a.get(high),mid,mid_num;

        while(high-low != 1){
            mid = (low+high)/2;
            mid_num = a.get(mid);
            if(mid_num > left && mid_num < right) return a.get(0); // this is to check when the array is not at all rotated
            if(mid_num < right && mid_num < left) {
                high = mid;
            }

            if (mid_num > right && mid_num > left) {
                low = mid;
            }

            left = a.get(low);
            right = a.get(high);
        }
         return right;

    }
    /*------------------------ End of the find minimum in a rotated array  ------------------------*/




    /*------------------------ Start of the occurrences sum ------------------------*/

    public static int findCount(final List<Integer> a, int b) {
        int size = a.size();
        int firstIndex = findFirstIndex(a,0,size-1,b);
        int lastIndex = findLastIndex(a,0,size-1,b);

        if(firstIndex == -1) return 0;


        return lastIndex-firstIndex +1;

    }

    private static int findFirstIndex(List<Integer> a, int low, int high, int num) {
        int mid, mid_num;
        mid = (high + low)/2;
        mid_num = a.get(mid);
        if(low == high){
            return low + 1;
        }
        if(low > high) {
            if(low == a.size() || high == -1){
                return -1;
            }else{
                return low + 1;

            }
        }

        if (mid_num >= num) {
            return findFirstIndex(a, low, mid - 1, num);
        }else{
            return findFirstIndex(a, mid +1, high, num);

        }


    }
    private static int findLastIndex(List<Integer> a, int low, int high, int num) {
        int mid, mid_num;
        mid = (high + low)/2;
        mid_num = a.get(mid);
        if(low == high){
            return high;
        }
        if(low > high) {
            if(low == a.size() || high == -1){
                return -1;
            }else{
                return high;

            }
        }

        if (mid_num <= num) {
            return findLastIndex(a, mid+1, high, num);
        }else{
            return findLastIndex(a, low,mid-1, num);
        }


    }

    /*------------------------ end of the occurrences sum ------------------------*/

    /*------------------------ Start of power function ------------------------*/
    public static int pow(int x, int n, int d) {
        long a = x;
        long res = 1L;

        while (n > 0) {

            if (n % 2 == 1) {
                res *= a;
                res %= d;
            }

            a *= a;
            a %= d;
            n = n >> 1;

        }

        res = (res + d) % d;

        return (int) res;

    }
    /*------------------------ end of power function ------------------------*/

    public static ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(findFirstIndex(a,0,a.size()-1,b));
        if(indexes.get(0) == -1){
            indexes.add(-1);
        }else{
            indexes.add(findLastIndex(a,0,a.size()-1,b));
        }

        return indexes;

    }


}
