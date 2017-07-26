package interviewBit.Numbers;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import java.util.*;

/**
 * Created by vgade on 6/21/17.
 */
public class MathPractice {
    public static void main(String[] args){
        System.out.println(reverse(1534236469));
    }


    /*----------------reverse and interger---------*/
    /*public static int reverse(int x) {
        int result = 0;
        return result;
    }*/
    /*----------------reverse and interger---------*/

    public static int solve(int[] A, int B, int C) {
        String num = Integer.toString(C);
        int size = num.length();
        int strt_digit = Integer.parseInt(String.valueOf(num.charAt(0)));
        int sol = 0;
        boolean exists = false;
        boolean zero_exits = false;
        if(A[0] == 0) zero_exits = true;
        if(size > B){

        }else if(size<B){
            return 0;
        }else{
            int count = 0;
            for (int i: A) {
                if(i < strt_digit){
                    count ++;
                }else if(i == strt_digit){
                    exists = true;
                    break;

                }else{
                    break;
                }
            }

            if(zero_exits){
                sol += (count-1)*(size-1) + count -1;
            }else{
                sol += count*(size-1);
            }

            if(exists) {
                
            }
        }

        return sol;
    }

    /* Number of zeroes*/
    public static int trailingZeroes(int A) {
        int zeros = 0;
        int fives = 5;
        int dividend = A;

        while(dividend/fives >= 1){
            zeros = zeros + dividend/fives;
            fives *=5;

        };
        return zeros;
    }

    public static int factorial(int a){
        int product = 1;
        for (int i = 1; i <=a ; i++) {
            product = product*i;
        }
        return product;
    }


    /*Reverse an integer with converitn to string*/
    public static int reverse(int a) {
        int reverse = 0;
        int divisor = 10;
        int dividend = Math.abs(a);
        boolean flag = false;
        if (a < 0) {
            flag = true;
        }
        int size = (int) Math.log10(dividend) + 1;
        int digit = 0;
        int count = 0;
        while (true) {
            digit = dividend % divisor;
            int mutiple = (int) Math.pow(10, size - 1 - count);
            reverse += digit * mutiple;
            dividend = dividend / divisor;
            count++;
            if (count == size) {
                break;
            }
        }

        if (flag) {
            reverse = -1 * reverse;
        }

        if(reverse < Integer.MIN_VALUE || reverse > Integer.MAX_VALUE) return 0;
        return reverse;
    }

    public static boolean isPower(int a) {
        int num_root = (int )Math.sqrt(a);
        boolean is_pow = false;
        if(a == 1) return true;
        for (int i = 2; i <= num_root; i++) {
            if(a%i == 0){
                int k = a;
                while(k%i == 0){
                    k = k/i;
                }
                if(k == 1){
                    is_pow = true;
                }
            }
        }
        return is_pow;

    }

    /*FizzBuzz*/
    public ArrayList<String> fizzBuzz(int A) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= A ; i++) {
            if(i%3 == 0 && i %5 == 0){
                list.add("FizzBuzz");
            }else if(i%3 ==0){
                list.add("Fizz");
            }else if(i%5 == 0){
                list.add("Buzz");
            }else{
                list.add(Integer.toString(i));
            }
        }
        return list;
    }
    /*Palindrome Integer*/

    public static boolean isPalindrome(int a) {
        boolean flag = false;
        if(a >= 0){
            String original = Integer.toString(a);
            int head = 0;
            int tail = original.length()-1;
            if(head == tail) return true;
            while (head<tail){
                if(original.charAt(head) != original.charAt(tail)){
                    flag = false;
                    break;
                }else{
                    flag = true;
                }
                head ++;
                tail --;
            }
        }
        return flag;
    }

    public static int titleToNumber(String a) {
        char[] alpha_array = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        HashMap<Character,Integer> alpha_mapping = new HashMap<>();
        for (int i = 0; i < alpha_array.length ; i++) {
            alpha_mapping.put(alpha_array[i],i+1);
        }

        int size = a.length();
        int col_num = 0;
        for (int i = 0; i < size; i++) {
            int pos_val  = alpha_mapping.get(a.charAt(i));
            col_num += pos_val*Math.pow(26,size-i-1);

        }
        return col_num;
    }

    public static int gcd(int a, int b) {
        if(a ==0){
            return b;
        }else if(b==0){
            return a;
        }
        HashMap pf_a = primeFactorization(a);
        HashMap pf_b = primeFactorization(b);
        Set<Integer> a_keys = pf_a.keySet();
        Set<Integer> b_keys = pf_b.keySet();
        Set<Integer> factors = new HashSet<>();
        boolean flag = true;
        if(a_keys.containsAll(b_keys) || b_keys.containsAll(a_keys)){
            factors = a_keys.size() > b_keys.size() ? b_keys:a_keys;
        }else {
            flag = false;
            return 1;
        }
        int gcd = 1;
        for (int f : factors) {
            if (flag) {
                int a_mul = (int) pf_a.get(f);
                int b_mul = (int) pf_b.get(f);
                if(a_mul < b_mul){
                    gcd = (int) (gcd * Math.pow(f, a_mul));
                }else {
                    gcd = (int) (gcd * Math.pow(f, b_mul));
                }
            } else {
                int mul = 1;
                try {
                    mul = (int) pf_a.get(f);
                }catch (NullPointerException e){
                    mul = (int) pf_b.get(f);
                }
                gcd = (int) (gcd * Math.pow(f, mul));
            }
        }
        return gcd;
    }


    public static HashMap<Integer,Integer> primeFactorization(int a){
        int root_num = (int) Math.ceil(Math.sqrt(a)) + 1;
        HashMap<Integer,Integer> pFactors = new HashMap<>();
        for (int i = 2; i <= root_num ; i++) {
            if (a%i == 0){
                int count = 0;
                while(a%i == 0){
                    a = a/i;
                    count++;
                }
                pFactors.put(i,count);
            }
        }
        return pFactors;
    }
}
