import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sort {
    public static void main(String[] args){
        int[] list = {4,3,2,1,7,5};
        quickSort(list);
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

    /*----
    * Quick sort. One of the most efficient in place sorting algorithm with divide and conquer paradigm.
    *  worst case is O(n2) but average case runs in O(nlgn) with right choide of the pivot.
    * */

    public  static int[] quickSort(int[] input){
        recursion(input,0,input.length-1);
        return input;
    }

    private static void recursion(int[] input, int left, int right) {
        if(left >= right){
            return;
        }
        int q = partision(input,left,right);
        recursion(input,left,q-1);
        recursion(input,q+1,right);
    }

    private static int partision(int[] input, int left, int right) {
        int pivot = left;
        left++;
        int temp;
        while (left<=right){
            if(input[left] < input[pivot]){
                temp = input[left];
                input[left] = input[pivot];
                input[pivot] = temp;
                pivot = left;
                left++;
            }else{
                temp = input[left];
                input[left] = input[right];
                input[right] = temp;
                right--;
            }
        }
        return pivot;
    }




}
