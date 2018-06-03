package interviewBit;

import java.lang.reflect.Array;
import java.util.*;


public class DynamicProgramming {
    public static void main(String[] args){
//        int [] array = {69, 54, 19, 51, 16, 54, 64, 89, 72, 40, 31, 43, 1, 11, 82, 65, 75, 67, 25, 98, 31, 77, 55, 88, 85, 76, 35, 101, 44, 74, 29, 94, 72, 39, 20, 24, 23, 66, 16, 95, 5, 17, 54, 89, 93, 10, 7, 88, 68, 10, 11, 22, 25, 50, 18, 59, 79, 87, 7, 49, 26, 96, 27, 19, 67, 35, 50, 10, 6, 48, 38, 28, 66, 94, 60, 27, 76, 4, 43, 66, 14, 8, 78, 72, 21, 56, 34, 90, 89};
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i: array) {
//            list.add(i);
//        }

//        int[][] arr2d = {
//                {-8,5, 7},
//                {3,7,-8},
//                {5,-8, 9}
//        };

        //int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(fibonnacciSeriesMDP(10));
    }
    // with recursion and will have O(n)
    public static int fibonnacciSeries(int n){
            if (n <= 1)
                return n;
            return fibonnacciSeries(n-2)+ fibonnacciSeries(n-1);

    }
    // matrix DP
    public static int fibonnacciSeriesMDP(int n){
        int[] dp = new int[n];
        if(n == 1) return 0;
        if(n == 2) return 1;
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i < n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n-1];

    }

    /* Start of Tesla Test*/



    public static int solution(int[] A) {
        // write your code in Java SE 8
        HashMap<Integer,Integer> map = new HashMap<>();
        int result = Integer.MAX_VALUE;
        // Initialization of map
        for (int i = 1; i <=6 ; i++) {
            map.put(i,0);
        }
        // Number of elements
        for (int i = 0; i < A.length ; i++) {
            map.put(A[i],map.get(A[i])+1);
        }

        for (int i = 1; i <= 6 ; i++) {
            int count = 0;
            for (int j = 1; j <=6 ; j++) {
                if(i!=j){
                    if(i+j == 7){
                        count += (map.get(j)*2);
                    }else {
                        count += map.get(j);
                    }
                }
            }
            if(count<result) result = count;
        }

        return result;
    }

    public static int giraffe(int[] arr){
        int size = arr.length;
        int max = -1,count =0;
        Stack<Integer> giff = new Stack<>();
        for(int i=0;i<size;i++)
        {
            if(arr[i] > max)
            {
                giff.push(arr[i]);
                count++;
                max = arr[i];
            }
            else if(arr[i] < giff.peek())
            {
                while(arr[i] < giff.peek())
                {
                    giff.pop();
                    count--;
                    if(giff.empty()) break;
                }
                giff.push(arr[i]);
                count++;
            }

        }
        return count;
    }
    /* End of Tesla test*/

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int row = A.size();
        if(row == 0) return 0;
        int col = A.get(0).size();
        int[][] mat = new int[row][col];
        int count = 0;
        mat[0][0] = A.get(0).get(0);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col ; j++) {
                if(i==0 && j == 0) continue;
                if(i == 0){
                    mat[i][j] = A.get(i).get(j) + mat[i][j-1];
                }else if(j == 0){
                    mat[i][j] = mat[i-1][j] + A.get(i).get(j);
                }else{
                    mat[i][j] = A.get(i).get(j) + mat[i][j-1] + mat[i-1][j];
                }

                if(mat[i][j] == 0) count++;
            }
        }
        return count;

    }



    public static int lis(final List<Integer> A) {
        int[]mat = new int [A.size()];
        Arrays.fill(mat, 1);
        if(A.size() == 0){
            return 0; // if the list is empty
        }
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < A.size(); i++){
            for (int j = 0; j <i ; j++) {
                if(A.get(j)< A.get(i) && mat[i] < mat[j]+1) {
                    mat[i] = mat[j] + 1;
                }
            }
            if(result < mat[i]) result = mat[i];
        }

        return result;
    }


    /* Chords of a circle*/
    public static int chordCnt(int A) {
        int n_point = 2*A;
        int result= 0;
        HashMap<Integer,Integer> chords_map = new HashMap<>();
        chords_map.put(2,1);
        int start = 1;
        int left_half = 0;
        int right_half = 0;

        for (int i = 4; i <= n_point ; i=i+2) {
            result = 0;
            int j = 2;
            for (; j < (i/2)+1; j=j+2) { // Initial point is one.
                right_half = j - start -1;
                left_half = i-j;
                if(right_half >= 4){
                    result = (result%1000000007 + ((chords_map.get(left_half)%1000000007)*(chords_map.get(right_half)%1000000007)))%1000000007;
                }else{
                    result = (result%1000000007 + (chords_map.get(left_half)%1000000007))%1000000007;
                }
            }
            result = (result%1000000007 + (result%1000000007))%1000000007;

            if(j == (i/2)+1){
                result = (result%1000000007 + ((chords_map.get(j-2)%1000000007)*(chords_map.get(j-2)%1000000007)))%1000000007;
            }
            chords_map.put(i,result%1000000007);
        }
        return (chords_map.get(n_point));
    }

    /* forming the circle- circular tour problem.*/
    public static int circularTour(int n, String input){
        String[] details = input.split(" ");
        int[] distance = new int[n+1];
        int[] petrol = new int[n+1];
        int[][] distance_matrix = new int[n+1][n+1];
        int[][] fuel_matrix = new int[n+1][n+1];
        for (int i = 0; i < 2*n ; i++) {
            if(i%2 == 0){
                petrol[(i/2) +1] = Integer.parseInt(details[i]);
            }else{
                distance[(i+1)/2] = Integer.parseInt(details[i]);
            }
        }

        buildMatrix(distance_matrix,distance, n);
        buildMatrix(fuel_matrix,petrol,n);

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1 ; j++) {
                fuel_matrix[i][j] = fuel_matrix[i][j]-distance_matrix[i][j];
            }
        }
        for (int i = 1; i < n+1; i++) {
            int prev = i == 1 ? n : i-1;
            int next = i == n ? 1 : i +1;
            if(fuel_matrix[prev][i] - distance[prev] >=0 || fuel_matrix[next][i]-distance[i]>=0){
                return 1;
            }
        }
        return -1;
    }

    private static void buildMatrix(int[][] matrix, int[] input, int size) {
        for (int i = 1; i < matrix.length ; i++) {
            for( int j = i+1; j > i && (j % size != i%size) ; j ++){
                int prev = (j-1)%size==0? size :(j-1)%size;
                int present = j%size ==0 ? size : j%size;
                matrix[i][present] = matrix[i][prev] + input[prev];
            }
        }
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

    /*public static int anytwo(String a) {
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
    }*/

    public static int anytwo(String a) {
        int result = 0;
        HashSet<String> occurrences = new HashSet<>();
        result = subSequence(a, 0, 2,occurrences ,result);
        return result;
    }

    private static int subSequence(String a, int left, int size,HashSet occurrences,int result) {
        if(size == a.length()){
            return 0;
        }
        if(left + size > a.length()){
            size = size + 1;
            left = 0;
        }else if(left + size <= a.length()){
            String str = a.substring(left,left + size);
            if(occurrences.contains(str)){
                return 1;
            }else{
                occurrences.add(str);
            }
            left = left + 1;
        }
        return subSequence(a, left,size,occurrences,result);
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
    public static int longestCommonSubsequenceDP(String a, String b){
        int size_a = a.length();
        int size_b = b.length();
        int result = 0;
        int[][] dp_matrix = new int[size_a][size_b];
        for (int i = 0; i < size_a; i++) {
            for (int j = i; j < size_b; j++) {
                if(a.charAt(i) == b.charAt(j)){
                    if(i == 0 ){
                        dp_matrix[i][j] = 1;
                    }else{
                        dp_matrix[i][j] = dp_matrix[i-1][j-1] + 1;
                    }
                }
                if(result < dp_matrix[i][j]) result = dp_matrix[i][j];
            }
        }

        return result;
    }

}
