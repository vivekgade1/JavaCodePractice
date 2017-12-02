package interviewBit.Numbers;

import java.lang.reflect.Array;
import java.util.*;


public class DynamicProgramming {
    public static void main(String[] args){


        int [] array = {-2,1,-3,4,-1,2,1,-5,4};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: array) {
            list.add(i);
        }

        /*int[][] arr2d = {
                {20, 29, 84, 4, 32, 60, 86, 8, 7, 37},
                {77, 69, 85, 83, 81, 78, 22, 45, 43, 63},
                {60, 21, 0, 94, 59, 88, 9, 54, 30, 80},
                {40, 78, 52, 58, 26, 84, 47, 0, 24, 60},
                {40, 17, 69, 5, 38, 5, 75, 59, 35, 26},
                {64, 41, 85, 22, 44, 25, 3, 63, 33, 13},
                {2, 21, 39, 51, 75, 70, 76, 57, 56, 22},
                {31, 45, 47, 100, 65, 10, 94, 96, 81, 14}
        };*/

        //System.out.println(minDistance("ba","aa"));
        //System.out.println(maxSubArray(array));
        //System.out.println(longestCommonSubsequence("AGGTAB","GXTXAGYTGAB"));

    }



    public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int[][] result = new int[a.size()][a.size()];
        result[0][0] = a.get(0).get(0);
        for (int i = 1; i < a.size(); i++) {
            ArrayList arr = a.get(i);
            for (int j = 0; j < arr.size(); j++) {
                if(j == 0){
                    result[i][j] = result[i-1][j] + (int)arr.get(j);
                }else if(j == arr.size()-1){
                    result[i][j] = result[i-1][j-1] + (int)arr.get(j);
                }else{
                    result[i][j] = Math.min(result[i-1][j] + (int)arr.get(j),result[i-1][j-1] + (int)arr.get(j));
                }

            }
        }
        Arrays.sort(result[a.size()-1]);
        return result[a.size()-1][0];

    }

    public static int maxSubArray(int[] nums) {
        int size = nums.length;
        int[] sum_track = new int[size];
        int result = 0;
        if(size == 0) return 0;
        sum_track[0] = nums[0];
        result = nums[0];
        for (int i = 1; i < size ; i++) {
            if(sum_track[i-1] + nums[i] > nums[i]){
                sum_track[i] = sum_track[i-1]+ nums[i];
            }else{
                sum_track[i] = nums[i];
            }
            if(sum_track[i] > result) result = sum_track[i];
        }

        return result;

    }

    public static int anytwo(String a) {
        HashMap<String, Integer> prev_str = new HashMap<>();
        int result = 0;
        int size = a.length();
        if(size > 2) {
            for (int i = 1; i < size ; i++) {
                int count = 1;
                while (count < i+1){
                    String sub_str = a.substring(i-count,i+1);
                    if(prev_str.get(sub_str)== null){
                        prev_str.put(sub_str,1);
                    }else{
                        return 1;
                    }
                    count++;
                }

            }
        }
        return  result;
    }

    public static int maxProfit(final List<Integer> a) {
        int size = a.size();
        int result = 0;
        int min = a.get(0);
        int diff = 0;
        if(size > 1) {
            for (int i = 1; i < size; i++) {
                if (a.get(i) > min) {
                    diff = a.get(i) - min;
                    if (result < diff) {
                        result = diff;
                    }
                }

                if (a.get(i) < min) {
                    min = a.get(i);
                }


            }
        }
        return result;
    }

    private static int minPathSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] cost = new int[m][n];

        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(i == 0 && j == 0){
                    cost[0][0] = a[0][0];
                }else{
                    if(i == 0){
                        cost[i][j] = cost[i][j-1]+ a[i][j];
                    }else if(j== 0){
                        cost[i][j] = cost[i-1][j]+ a[i][j];
                    }else{
                        cost[i][j] = a[i][j] + Math.min(cost[i][j-1],cost[i-1][j]);
                    }
                }

            }
        }
        return cost[m-1][n-1];

    }

    public static int minPathSum(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        int[][] cost = new int[m][n];

        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(i == 0 && j == 0){
                    cost[0][0] = a.get(i).get(j);
                }else{
                    if(i == 0){
                        cost[i][j] = cost[i][j-1]+ a.get(i).get(j);
                    }else if(j== 0){
                        cost[i][j] = cost[i-1][j]+ a.get(i).get(j);
                    }else{
                        cost[i][j] = a.get(i).get(j) + Math.min(cost[i][j-1],cost[i-1][j]);
                    }
                }

            }
        }
        return cost[m-1][n-1];
    }

    /*
    * Min distance using matrix.
    * */

    public static int minDistance(String a, String b) {
        int m = a.length();
        int n = b.length();
        int dp[][] = new int[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                    // If last character are different, consider all
                    // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]);
            }
        }

        return dp[m][n];

    }


    private static int min(int i, int i1, int i2) {
        int[] arr = {i,i1,i2};
        Arrays.sort(arr);
        return arr[0];
    }


    /*
    * Max sub array in the list
    *
    */
    /*public static void maxSubArray(int[] nums){
        HashMap<String,Integer> index_mapping = new HashMap<>();
        int[] sums = new int[nums.length];
        int size = nums.length;
        int result = 0;
        for (int i = 0; i < size - result ; i++) {
            for (int j = 0; j < size ; j++) {

            }
        }
    }

    public static int maxKeys(int n){
        int result = 0;
        return result;
       }
*/


    /* The stair case problem, The number of ways to reach n steps depends on ways to reach  n-1 and n-2 which are two different ways.*/

    public static int climbStairs(int a) {
        if(a ==0) return 0;
        if(a == 1) return 1;
        if(a ==2) return 2;
        int prev_step = 2;
        int before_prev_step = 1;
        int present = 0;


        for (int i = 3; i <= a; i++) {
            present = prev_step + before_prev_step;
            before_prev_step = prev_step;
            prev_step = present;
        }
        return present;


    }

    public static int numDecodings(String a) {

        if(Character.getNumericValue(a.charAt(0))==0)
        {
            return 0;
        }
        int n=a.length();
        int store[]=new int[n+1];
        store[0]=1;
        store[1]=1;
        for(int j=1;j<n;j++)
        {
            int num=Character.getNumericValue(a.charAt(j-1))*10;
            if(Character.getNumericValue(a.charAt(j))==0)
            {

                num+=Character.getNumericValue(a.charAt(j));
                if(num>=20)
                {
                    return 0;
                }
                else
                {
                    store[j+1]=store[j-1];
                    continue;
                }
            }
            num+=Character.getNumericValue(a.charAt(j));
            if(num<=26 && Character.getNumericValue(a.charAt(j-1))!=0)
            {
                store[j+1]=store[j]+store[j-1];
            }
            else
            {
                store[j+1]=store[j];
            }
        }
        return store[n];
    }

    public static int findNumberOfLIS(int[] A) {
        int[] result = new int[A.length];
        int max_len = 1;
        for (int i = 0; i < result.length ; i++) {
            result[i] = 1;
        }

        for (int i = 1; i < A.length ; i++) {
            for (int j = 0; j < i ; j++) {
                if(A[i] > A[j] && result[i] < result[j]+1){
                    result[i] = result[j] + 1;
                    if(result[i] > max_len) {
                        max_len = result[i];
                    }
                }
            }
        }
        int count = 0;
        if(max_len == 1) return A.length;
        for (int i = 0; i < result.length ; i++) {
            if(result[i] == max_len-1){
                count++;
            }
        }
        return count;

    }

    public static int longestSubsequenceLength(final List<Integer> A){
        int size = A.size();
        if(size ==0) return 0;
        int[] inc = new int[size];
        int[] dec = new int[size];
        int max_len = 0;

        for (int i = 0; i < size ; i++) {
            inc[i] = 1;
            dec[i] = 1;
        }

        for (int i = 1; i < size ; i++) {
            for (int j = 0; j < i ; j++) {
                if(A.get(i) > A.get(j) && inc[i] < inc[j]+1){
                    inc[i] = inc[j] + 1;
                }
                if(A.get(size-1-i) > A.get(size-1-j) && dec[size-1-i] < dec[size-1-j]+1){
                    dec[size-1-i] = dec[size-1-j] + 1;
                }
            }
        }

        for (int i = 0; i < size ; i++) {
            int ele = inc[i] + dec[i]-1;
            if(ele > max_len) max_len = ele;
        }

        return max_len;
    }

    /*
    * LCS Longest common subsequence
    * */

    public static int longestCommonSubsequence(String a, String b){
        int size_a = a.length();
        int size_b = b.length();
        int result = 0;
        int pointer = 0;
        StringBuilder seq = new StringBuilder();
        for (int i = 0; i < size_a ; i++) {
            for (int j = pointer; j < size_b ; j++) {
                if(a.charAt(i) == b.charAt(j)){
                    result++;
                    pointer = j+1;
                    seq.append(a.charAt(i));
                    break;
                }
            }
        }
        System.out.println(seq.toString());
        return result;
    }

}
