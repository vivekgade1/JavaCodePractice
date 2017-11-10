import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args){
        int[] list = {4,3,2,1,7,5};
        int[] result = mergeSort(list);
        for (int i:result) {
            System.out.println(i);
        }
    }

    /* This is similar to arrangement of cards
    * The complexity is O(n^2)
    * */

    public static int[] insertionSort(int[] nums){
        int key = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            int j = i-1;
            key = nums[i];
            while (j >=0 && nums[j] > key){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = key;
        }
        return nums;
    }

    /* Divide and conquer, contains O(n) extra space but the complexity if O(nlgn)
    *  It is not an in place sorting algorithm i.e if duplicates are present, the arrangement can be different.
    * */

    public static int[] mergeSort(int[]nums){
        if(nums.length == 1){
            return nums;
        }
        int size = nums.length;
        int mid = size/2;

        int[] left = mergeSort(Arrays.copyOfRange(nums,0,mid));
        int [] right = mergeSort(Arrays.copyOfRange(nums,mid,size));
        return merge(left,right);
    }

    public static int[] merge(int[] a, int[] b){
        int[] final_list = new int[a.length+b.length];
        int i=0,j=0,k=0;

        while (k < final_list.length){
            if(i == a.length && j != b.length){
                final_list[k] = b[j];
                j++;
            }else if(i != a.length && j == b.length){
                final_list[k] = a[i];
                i++;
            }else if(i != a.length && j != b.length){
                if(a[i] < b[j]){
                    final_list[k] = a[i];
                    i++;
                }else {
                    final_list[k] = b[j];
                    j++;
                }
            }
            k++;
        }
        return final_list;
    }
}
