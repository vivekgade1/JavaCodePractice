package interviewBit.Numbers;

import java.util.*;

/**
 * Created by vgade on 7/27/17.
 */
public class TwoPointers{
    public static void main(String [] args){
        Integer[] list1 = {-1};
        Integer[] list2 = {-2};
        Integer[] list3 = {-3};

        System.out.println(solve(Arrays.asList(list1),Arrays.asList(list2),Arrays.asList(list3)));
    }

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


}
