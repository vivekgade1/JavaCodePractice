package interviewBit;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vgade on 6/19/17.
 */
public class NumbersIntro {
    public static void main(String []  args){
        primeFactorization(100);
        //System.out.println();
    }
    /*Binary representation*/

    public static String binaryRepresentation(int a){
        StringBuilder s = new StringBuilder();
        while (a!= 1 && a != 0){
            s = s.append(Integer.toString(a%2));
            a = a/2;
        }
        s.append(a);
        s.reverse();
        return s.toString();
    }

    /*All factors*/
    public static ArrayList<Integer> allFactors(int a) {
        ArrayList<Integer> l = new ArrayList<>();
        int sqr_num = (int) Math.ceil(Math.sqrt(a));
        l.add(1);
        for(int i = 2; i <= sqr_num;i++){
            if( a%i == 0){
                if(l.indexOf(i) == -1)l.add(i);
                if(i != a/i && l.indexOf(a/i) == -1){l.add(a/i);}
            }
        }
        if(a!=1){
            l.add(a);
        }
        Collections.sort(l);
        return l;
    }
    /*Prime factorization of a number....
    * Run the loop till srqt of the number and an internal loop for power of the prime
    * */

    public static void primeFactorization(int a){
        ArrayList<Integer> factors = allFactors(a);
        int root_num = (int) Math.ceil(Math.sqrt(a));
        for (int i : factors) {
            if (a%i == 0){
                int count = 0;
                while(a%i == 0){
                    a = a/i;
                    count++;
                }
                System.out.println(i + " to power " + count);
            }
        }
    }

    /*
    Finding prime number from 1 to n using Sieve of Eratosthenes using arrays (more effective)
    * */

    public  static ArrayList primeNums(int a){
        ArrayList<Integer> prime_list = new ArrayList<>();
        int[] num_list = new int[a+1];

        for (int i = 2; i <=a ; i++) {
            if(num_list[i] == 0) {
                prime_list.add(i);
                int k = 2 * i;
                int count = 0;
                while (k <= a) {
                    try {
                        num_list[k] = 1;
                        k = k + i;

                    } catch (ArrayIndexOutOfBoundsException e) {
                        k = k + i;
                    }
                }
            }
        }
        return prime_list;
    }

    /*
    Finding prime number from 1 to n using Sieve of Eratosthenes using lists
    * */

    public  static ArrayList primeNumbers(int a){
        ArrayList<Integer> prime_list = new ArrayList<>();

        for (int i = 2; i <= a ; i++) {
            prime_list.add(i);
        }
        int indx = 0;

        while(indx < prime_list.size()){
            int p = prime_list.get(indx);
            int k = 2*p;
            while (k <= a){
                try {
                    prime_list.remove(prime_list.indexOf(k));
                    k = k + p;
                }catch (ArrayIndexOutOfBoundsException e){
                    k = k + p;
                }

            }
            indx = prime_list.indexOf(p)+1;
        }
        return prime_list;

    }

    /*Prime Verification */

    public static boolean primeVerification(int a){
        int sqr_num = (int) Math.ceil(Math.sqrt(a));
        boolean flag = true;
        for (int i = 2; i <= sqr_num ; i++) {
            if(a % i ==0){
                flag = true;
                return flag;
            }
        }

        return flag;

    }
}
