package LeetCode;

import java.util.*;

public class SetOne {
    public static void main(String[] args){
        int[] input = {3,3};
        lengthOfLongestSubstring("vivek");
    }

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> map = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length();i++) {
            map.add(s.charAt(i));
            for (int j = i+1; j < s.length() ; j++) {
                if(!map.contains(s.charAt(j))){
                    map.add(s.charAt(j));
                }else{
                    break;
                }
            }
            if(result < map.size()) result = map.size();
            map.clear();
        }
        return result;
    }

    public static int[] twoSum(int[] list, int sum) {
        int[] d_arr = list.clone();
        Arrays.sort(d_arr);
        int left = 0;
        int right = list.length-1;
        int[] result = {-1,-1};
        int[] values = new int[2];

        while (left < right){
            if(d_arr[right] == sum - d_arr[left]){
                values[0] = d_arr[left];
                values[1] = d_arr[right];
                left++;
            }else if(d_arr[right] > sum - d_arr[left]){
                right--;
            }else{
                left ++;
            }
        }

        for (int indx = 0; indx < 2; indx ++) {
            for (int i = 0; i < list.length ; i++) {
                if(values[indx] == list[i] && result[0] != i && result[1] != i){
                    result[indx] = i;
                }
            }
        }
        return result;

    }
}
