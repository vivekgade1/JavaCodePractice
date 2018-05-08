package interviewBit;

import java.util.Arrays;

public class SortingAlgo {
    public static void main(String[] args){
        int[] list = {213,23,2134,34};
        bubbleSort(list);

    }


    public static void bubbleSort(int[] a){
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j]= a[j+1];
                    a[j+1] = temp;
                    //swap(a[j], a[j + 1]);
                    count++;
                }
            }

        }
        System.out.println(count +  " " + a[0] + " " + a[n-1]);
    }

    /*--------------------------------------------- Insertion Sort -------------------------------------------------------------------------------------*/
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

    /*-----------------------------------------------------------------------------------------------------------------------------------------------*/

    /*--------------------------------------------- Merge Sort -------------------------------------------------------------------------------------*/
    /* Divide and conquer, contains O(n) extra space but the complexity if O(nlgn)
     *  It is not an in place sorting algorithm i.e if duplicates are present, the arrangement can be different.
     * */


    public static int[] mergeSort(int[] input){
        int size = input.length;
        if(size == 1){ // This is when the recursion reaches the end.
            return input;
        }
        return merge(mergeSort(Arrays.copyOfRange(input,0,size/2)),mergeSort(Arrays.copyOfRange(input,size/2,size)));
    }

    private static int[] merge(int[] left, int[] right) {
        // simple merging of two sorted lists.
        int l_size = left.length;
        int r_size = right.length;
        int[] merged_list = new int[l_size + r_size];
        int l=0,r=0,k=0;

        while (k < merged_list.length){
            if(l == l_size && r != r_size){
                merged_list[k] = right[r];
                r++;
            }else if(l != l_size && r == r_size){
                merged_list[k] = left[l];
                l++;
            }else if(l != l_size && r != r_size){
                if(left[l] < right[r]){
                    merged_list[k] = left[l];
                    l++;
                }else {
                    merged_list[k] = right[r];
                    r++;
                }
            }
            k++;
        }
        return merged_list;

    }
    /*----------------------------------------------------------------------------------------------------------------------------------*/


}
