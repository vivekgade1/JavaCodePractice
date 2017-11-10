package interviewBit.Numbers;

import javax.lang.model.type.NullType;
import java.util.*;

/**
 * Created by vgade on 7/27/17.
 */
public class TwoPointers{
    public static void main(String [] args){
        int[] list1 = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(list1,2));
        //System.out.println(maxConsecutiveOnes(list1));
    }


    public static int maxProfit(int[] prices, int fee) {
        int size = prices.length;
        int[][] profit = new int[size][size];
        for (int i = 0; i < size ; i++) {
            if(i != 0){
                int max = 0;
                for (int j = 0; j < i ; j++) {
                    if(max < profit[j][i-1]) {
                        max = profit[j][i-1];
                    }
                }
                if(i == size-1){
                    for (int j = 0; j < i ; j++) {
                        if(max < profit[j][i]) {
                            max = profit[j][i];
                        }
                    }
                }
                profit[i][i] = max;
            }
            for (int j = i+1; j < size ; j++) {
                if(i == 0){
                    if((prices[j]-prices[i]-fee) >= profit[i][j-1]){
                        profit[i][j] = prices[j]-prices[i]-fee;
                    }else{
                        profit[i][j] = profit[i][j-1];
                    }
                }else{
                    if(prices[j]- prices[i]- fee + profit[i][i] >= profit[i][j-1]){
                        profit[i][j] = prices[j]-prices[i]-fee + profit[i][i];
                    }else{
                        profit[i][j] = profit[i][j-1];
                    }
                }
            }
        }
        return profit[size-1][size-1];
    }


    /* Finding the paths with blocks as 1 or 0*/

    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        if(m == 0) return 0;
        int n = a.get(0).size();
        int [][] result= new int[m][n];
        if(m == 1 && n == 1){
            if(a.get(0).get(0) == 0){
                return 1;
            }else {
                return 0;
            }
        }
        result[0][0] = 1;
        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j ++){
                if(i == 0 && j != 0 ){
                    if(a.get(i).get(j-1) == 0){
                        result[i][j] = 1;
                    }
                }else if(i != 0 && j == 0){
                    if(a.get(i-1).get(j-1) == 0){
                        result[i][j] = 1;
                    }
                }else{
                    if(a.get(i-1).get(j) == 0 && a.get(i).get(j-1) == 0){
                        result[i][j] = result[i-1][j] + result[i][j-1];
                    }else if(a.get(i-1).get(j) == 0){
                        result[i][j] = result[i-1][j];
                    }else if(a.get(i).get(j-1) == 0){
                        result[i][j] = result[i][j-1];
                    }else{
                        result[i][j] = 0;
                    }
                }
            }
        }
        return result[m-1][n-1];
    }

    public static boolean checkInclusion(String s1, String s2) {
        boolean result=false;
        int length = s1.length();
        //Set<String> all_perm= new HashSet<>();
        //permute(s1,0 , length-1,all_perm);
        for (int i = 0; i < s2.length() - length +1 ; i++) {
            if (s1.indexOf(s2.charAt(i)) != -1) {
                /*if(all_perm.contains(s2.substring(i,i+length))){
                    result = true;
                    break;
                }*/
                StringBuilder d_s1 = new StringBuilder(s1);
                for (int j = i; j < i +length ; j++) {
                    int indx = d_s1.toString().indexOf(s2.charAt(j));
                    if(indx != -1){
                        d_s1.deleteCharAt(indx);
                    }else{
                        break;
                    }
                }
                if(d_s1.length() == 0){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    private static void permute(String str, int l, int r, Set<String> result)
    {
        if (l == r) result.add(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r,result);
                str = swap(str,l,i);
            }
        }
    }


/*CLosest three sum */

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
    public static int maxArea(int[] height) {
        int low = 0;
        int high = height.length -1;
        int capacity = 0;
        while(low < high){
            int h = Math.min(height[low],height[high]);
            capacity = Math.max(capacity,h*(high-low));
            while(height[low]<= h && low < high) low ++;
            while(height[high]<= h && low < high) high --;
        }
        return capacity;
    }


    /*public static boolean checkInclusion(String s1, String s2) {
        boolean result = false;
        int size = s1.length();
        if(s1.length() > s2.length()) return false;
        for (int i = 0; i < s2.length()-size ; i++) {
            if(s1.contains(Character.toString(s2.charAt(i)))){
                String sub = s2.substring(i+1,i+size);
                System.out.println(sub);
                int count = 1;
                for (int j = 1; j < sub.length() ; j++) {
                    if(!s1.contains(Character.toString(sub.charAt(j)))){
                        i = i+count;
                        break;
                    }else{
                        count  ++;
                    }
                }
                if(count == sub.length()+1){
                    return true;
                }
            }
        }
        return result;
    }*/

    /* If the numbers in the arrays are heights, find the maximum capacity of water that that structure formed can hold.*/
    public static int trap(int[] height) {
        int result = 0;
        int peak = 0;
        int left = 0, right= 1;
        int sum = 0;
        int max_capacity = 0;
        while (right < height.length) {
            if (height[right] < height[left]) {
                sum += height[right];
            } else {
                max_capacity = (right - left-1) * height[left];
                result += max_capacity - sum;
                sum = 0;
                left = right;
                peak = right;
            }
            right++;
        }
        if(peak != right-1){
            int max_peak = peak;
            right = right -1;
            sum = 0;
            peak = right;
            for (int i = height.length-2; i >= max_peak ; i--) {
                if (height[i] < height[right]) {
                    sum += height[i];
                } else {
                    max_capacity = (right-i-1) * height[right];
                    result += max_capacity - sum;
                    sum = 0;
                    right = i;
                    peak = right;
                }


            }
        }

        return result;


    }

    /* Given an array of 1's and 0's, you are allowed to flip one zero. Find the maximum number of consecutive 1's */


    public static int maxConsecutiveOnes(int[] list){
        int result = 0;
        int size = list.length;
        if(size == 1){
            return 1;
        }
        int prev_end = 0;
        int prev_length = 0;
        boolean included = false;
        for (int low = 0; low < size; low++) {
            included = false;
            if(list[low] == 1){
                int high = low + 1;
                int sub_len = 1;
                while (high < size && list[high] == 1){
                    sub_len ++;
                    high ++;
                }

                if(sub_len + 1 <= size) sub_len ++;

                if(low - prev_end == 2){
                    sub_len = prev_length + sub_len;
                    included = true;
                }

                if(sub_len > result){
                    result = sub_len;
                }

                low = high -1;
                prev_end = low;
                if(!included){
                    prev_length = sub_len-1;
                }else{
                    prev_length = sub_len - prev_length -1;
                }
            }
        }
        // this is for the case where all the elements int he list are zeroes
        if(result == 0){
            result = 1;
        }

        return result;
    }

}
