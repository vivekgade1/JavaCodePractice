import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Solution {



/*    public static void main(String args[] ) throws Exception {
        *//* Enter your code here. Read input from STDIN. Print output to STDOUT *//*
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        int count = 0;
        String line;
        ArrayList<String> input = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            if(count%2 == 0 && count != 0){
                input.add(line);
            }
            count++;
        }

        for (String input_str: input) {
            incrementedString(input_str);
        }


    }*/

    public static void main(String [] args){
        String [] x = {"adsjfshakjH"};
//        ArrayList<Integer> l = new ArrayList<>();
//        for (int i : x) {
//            l.add(i);
//        }
        MergeStrings(x);

    }

    static String MergeStrings(String[] strings) {
        String alpha_map = "abcdefghijklmnopqrstuvwxyz";
        HashMap<Character,Integer> map  = new HashMap<>();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < alpha_map.length(); i++) {
            map.put(alpha_map.charAt(i),0);
        }

        for (int i = 0; i < strings.length ; i++) {
            char[] arr_str = strings[i].trim().toCharArray();

            for (int j = 0; j < arr_str.length; j++) {

                if(Character.isAlphabetic(arr_str[j]) && Character.isLowerCase(arr_str[j])) {
                    int count = map.get(arr_str[j]);
                    map.put(arr_str[j], count + 1);
                }
            }
        }

        for (int i = 0; i < alpha_map.length(); i++) {
            int size = map.get(alpha_map.charAt(i));
            char alpha = alpha_map.charAt(i);
            for (int j = 0; j < size; j++) {
                result.append(alpha);
            }
        }
        return result.toString();

    }

    // Indeed Prime problems

    public static void incrementedString(String str){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int size = str.length();
        HashMap <Character,Integer>occurences = new HashMap<>();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < size; i++) {
            int count = occurences.get(str.charAt(i)) == null ? 0 : occurences.get(str.charAt(i));
            char curr = str.charAt(i);
            if(count == 0){
                result.append(curr);
                occurences.put(curr,count+1);
            }else{
                int sum = alpha.indexOf(curr) + count;
                int pos = sum > 25 ? (sum%25)-1: sum%25;
                result.append(alpha.charAt(pos));
                occurences.put(curr,count+1);
            }
        }
        System.out.println(result.toString());
    }





    public static int repeatedNumber(final List<Integer> a) {
        Collections.sort(a);
        int dnum = -1;
        for (int i = 0; i < a.size()-1 ; i++) {
            int j = i + 1;
            int x = a.get(i);
            int y = a.get(j);
            if( x == y){
                dnum = a.get(i);
                break;
            }
        }
        return dnum;
    }

    public static int firstMissingPositive(ArrayList<Integer> a) {
        int max = Collections.max(a);
        int missNum = 1;
        if(max < 0){
            return missNum;
        }

        for (int i = 1; i < max ; i++) {
            if(a.indexOf(i) == -1){
                missNum = i;
                break;
            }
        }
        return missNum;

    }

    public static ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        int size = A.size();
        int startIndx =0 ;
        int endIndx = size-1;
        int tail = 0;
        int frnt = size -1;

        boolean strtFlag = false,endFlag = false;

        while (!(strtFlag && endFlag)){
            if(strtFlag && endFlag) break;
            if(startIndx > endIndx) break;
            List<Integer> tailList = A.subList(tail,startIndx +1);
            List<Integer> frontList = A.subList(endIndx,frnt+1);
            List<Integer> windowList = A.subList(startIndx,endIndx+1);
            if(windowList.size() == 0) break;
            if(Collections.max(windowList) <= Collections.min(frontList)){
                endIndx--;
            }else endFlag =true;

            if(Collections.min(windowList) >= Collections.max(tailList)){
                startIndx++;
            }else strtFlag = true;
        }


        ArrayList<Integer> indexesList = new ArrayList<>();

        if(!(strtFlag&&endFlag)){
            indexesList.add(-1);
        }else{
            indexesList.add(startIndx);
            indexesList.add(endIndx);
        }
        return indexesList;
    }

    public static ArrayList<Integer> subsort(ArrayList<Integer> A) {
        int size = A.size();
        int startIndx =0 ;
        int endIndx = size-1;
        int j = size-1;
        int i = 0;
        int prevNum = 0;
        int nextNum = 0;
        boolean strtFlag = false,endFlag = false;
        ArrayList<Integer> indexesList = new ArrayList<>();

        while(true){
            if(strtFlag && endFlag) break;
            if(i>size-1 || j<0)break;
            if(i == 0) prevNum = A.get(0);
            else prevNum = A.get(i-1);

            if(j == size-1) nextNum = A.get(size-1);
            else nextNum = A.get(j + 1);
            int presentF = A.get(i);
            int presentT = A.get(j);

            if(strtFlag && A.get(startIndx) == presentT){
                endIndx = j+1;
                endFlag = true;
            }

            if(endFlag && A.get(endIndx) == presentF){
                startIndx = i-1;
                strtFlag = true;
            }

            if(!strtFlag) {
                if (presentF >= prevNum) {
                    i++;
                } else {
                    startIndx = i-1;
                    strtFlag = true;
                }
            }
            
            if(!endFlag) {
                if (presentT <= nextNum) {
                    j--;
                } else {
                    endIndx = j+1;
                    endFlag = true;
                }
            }

        }

        if(!(strtFlag&&endFlag)){
            indexesList.add(-1);
        }else{
            indexesList.add(startIndx);
            indexesList.add(endIndx);
        }

        return indexesList;
    }

    int firstDuplicate(int[] a) {
        Set<Integer> map = new HashSet<Integer>();

        for(int i = 0; i < a.length; i++){
            if(map.contains(a[i])){
                return a[i];
            }else{
                map.add(a[i]);
            }
        }
        return -1;

    }

    char firstNotRepeatingCharacter(String s) {
        Set<Character> map = new HashSet<Character>();
        char result = '_';
        int indx = 0;
        for(int i = 0; i < s.length(); i++){
            if(map.contains(s.charAt(i))){
                continue;
            }else{
                map.add(s.charAt(i));
                if(i>=0 && i <= indx){
                    indx = i;
                    result = s.charAt(i);
                }
            }
        }
        return result;
    }



}
