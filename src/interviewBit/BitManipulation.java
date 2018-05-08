package interviewBit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BitManipulation {
    public static void main(String[] args){
        int [] array = {8,9};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: array) {
            list.add(i);
        }
        long i = 4294967295l;
        System.out.println(reverse(i));
    }

    public static long reverse(long a) {
        long max = Long.MAX_VALUE;
        long[] num = new long [32];
        long ele = a;
        int count = 31;
        while (count >=0){
            num[count] = ele%2;
            ele = ele/2;
            count--;
        }
        long result = 0;
        for (int i = 31; i >=0 ; i--) {
            result += Math.pow(2,31-i)*num[31-i];
        }
        return result;

    }
}
