package interviewBit;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args){
        int[] input_arr = {5,9,6,3,4,8,7,1,1,2,5,7,4,2,3};
//        ArrayList<Integer> input_list = new ArrayList<>();
//        for (int i = 0; i < input_arr.length ; i++) {
//            input_list.add(i,input_arr[i]);
//        }
        System.out.println(twoSum(input_arr,6));

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        Set<Integer> list = new HashSet<Integer>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            list.add(A[i]);
            if(A[i]>max){
                max = A[i];
            }
            if(A[i]<min){
                min = A[i];
            }
        }

        if(max<0){
            return 1;
        }

        if(min < 0){
            min = 1;
        }

        for (int i = min; i <= max; i++) {
            if(!list.contains(i)){
                return i;
            }
        }

        return max+1;
    }

    /*Goal is to find the max positive subarray
    *
    * */

    public ArrayList<Integer> maxset(ArrayList<Integer> a) {
	    long max = -1 ;
        int L = 0;
        int R = 0;
        int count = 0 ;
        for (int i = 0; i < a.size() ; i++) {
            long sum = -1;
            int start = i;

            if(a.get(i) >= 0){
                while(i < a.size() && a.get(i) >= 0){
                    sum += a.get(i);
                    i++;
                }

                if(max<sum){
                    max = sum;
                    L = start;
                    R = i;
                }else if(max == sum){
                    if(i - start > R - L){
                        L = start;
                        R = i;
                    }else if (i - start == R - L){
                        if(start < L){
                            L = start;
                            R = i;
                        }
                    }
                }
                i--;
            }else{
                count += 1;
            }
        }
        if(count == a.size()){
            return new ArrayList<>();
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = L ; i < R; i++) {
            list.add(a.get(i));
        }
        return list;
	}


    public static int maxSubArrayDP(final List<Integer> X) {
        int size = X.size();
        int[][] matrix = new int[size][size];
        int max = X.get(0);
        int result = max;
        int val;
        for (int i = 0; i < size ; i++) {
            for (int j = i; j < size; j++) {
                if(j ==0 && i == 0){
                    continue;
                }else if(i == j){
                    val = X.get(j);
                    max = matrix[i-1][j-1] + val;

                }else{
                    val = X.get(j);
                    max = matrix[i][j-1] + val;
                }
                matrix[i][j] = val >= max ? val : max ;
                if(matrix[i][j] >= result){
                    result = matrix[i][j];
                }
            }
        }
        return result;
    }

    public static int maxSubArray(final List<Integer> X){
        return recursiveSum(X,0, X.size()-1,Integer.MIN_VALUE);
    }

    public static int recursiveSum(List<Integer> X,int left, int right, int result){
        return 0;
    }

    /* two sum*/
    public static ArrayList<String> twoSum(int[] list , int sum ){
        Arrays.sort(list);
        ArrayList<String> result = new ArrayList<>();
        int left = 0;
        int size = list.length;
        int right = list.length - 1;
        while (left < right){
            if(list[right] == sum - list[left]){
                result.add(Integer.toString(list[left]) + "," + Integer.toString(list[right]));
                left++;
            }else if(list[right] > sum - list[left]){
                right--;
            }else{
                left ++;
            }
        }
        return result;
    }
}
