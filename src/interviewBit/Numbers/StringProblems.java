package interviewBit.Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vgade on 7/20/17.
 */
public class StringProblems {
    public static void main(String[] args){
        //String[] list = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static ArrayList<String> restoreIpAddresses(String a) {
        final int max_len = 16;
        final int min_len = 4;
        final int max = 225;
        final int size = a.length();
        ArrayList<String> ip_list = new ArrayList<>();
        StringBuilder ip = new StringBuilder();
        ip_list = recursiveBuilding(a,3,ip,size,ip_list);
        return ip_list;
    }

    public static ArrayList recursiveBuilding(String num, int indx, StringBuilder ip, int size, ArrayList ip_list){
        for (int i = indx; i >0 ; i--) {
            int address = Integer.parseInt(num.substring(0,indx));
            if( address<= 225 && address >0){
                ip.append("." + address);
                recursiveBuilding(num.substring(indx),i,ip,size,ip_list);
                if(ip.length() > size);
            }else{
                ip = new StringBuilder();
            }
        }
        return ip_list;
    }
    /*-------------------------------Compare versions ---------------------------------------------*/
    public static int compareVersion(String a, String b) {
        String [] list_a = a.replace(".","-").split("-");
        String [] list_b = b.replace(".","-").split("-");
        int size = list_a.length > list_b.length ? list_b.length: list_a.length;

        for (int i = 0; i < size ; i++) {
            if(compareStrs(list_a[i],list_b[i]) == 1){
                return 1;
            }else if(compareStrs(list_a[i],list_b[i]) == -1){
                return -1;
            }
        }

        if(list_a.length > list_b.length) {
            if(!list_a[list_b.length].equals("0")){
                return 1;
            }
        } else if(list_a.length < list_b.length) {
            if(!list_b[list_a.length].equals("0")){
                return -1;
            }
        }
        return 0;
    }

    public static int compareStrs(String a, String b){
        if (a.length() != b.length()) {
            int low = 0;
            while (a.charAt(low) =='0'){
                low ++;
            }
            a = a.substring(low);
            low = 0;
            while (b.charAt(low) =='0'){
                low ++;
            }
            b = b.substring(low);
        }

        if(a.length() > b.length()){
          return 1;
      }else{
          if(a.length() == b.length()){
              for (int i = 0; i < a.length(); i++) {
                  if(Character.getNumericValue(a.charAt(i)) > Character.getNumericValue(b.charAt(i))){
                      return 1;
                  }else if(Character.getNumericValue(a.charAt(i)) < Character.getNumericValue(b.charAt(i))){
                      return -1;
                  }
              }
              return 0;
          }else{
              return -1;
          }
      }
    }
    /*------------------------------- end of Compare versions ---------------------------------------------*/

    /*------------------------------------Interview Bit ATIO------------------------------------------------------*/
    public static int atoi(final String a) {
        int result = 0;
        int low = 0;
        boolean is_neg = false;
        if(a.length() == 0) return 0;
        String first_num = a.split(" ")[0];

        // check for empty or grabage at the beginnning
        if(first_num.charAt(0) == '-'){
            is_neg = true;
            low++;
        }else if(first_num.charAt(0) == '+'){
            low++;
        }

        if(low == first_num.length()) return 0;
        if(!Character.isDigit(first_num.charAt(low))) return 0;

        // get the size of the number int he first string
        int size = 1;
        while (size < first_num.length() && Character.isDigit(first_num.charAt(size))){
            size++;
        }
        // desired number and reset low and size to that desired number
        String desired_number = first_num = first_num.substring(low,size);
        low = 0;
        size = desired_number.length();

        while (low < size){
            if(Character.isDigit(desired_number.charAt(low))){
                int digit = Character.getNumericValue(desired_number.charAt(low));
                result += digit*(Math.pow(10,size-low-1));
            }else{
                break;
            }
            low ++;
        }

        if(is_neg) result *= -1;

        if(result >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if(result < Integer.MIN_VALUE || result == Integer.MIN_VALUE +1) {
            return Integer.MIN_VALUE;
        }
        return result;
    }
    /*------------------------------------- end Interview Bit ATIO -----------------------------------------------*/

    /*------------------------------------Justified text------------------------------------------------------*/
    public static ArrayList<String> fullJustify(List<String> a, int b) {
        int count=0;
        int left_indx=0;
        ArrayList<String> result_list = new ArrayList<>();
        for (int i = 0; i < a.size() ; i++) {
            if(count<=b){
                count+=a.get(i).length();
            }else {
                int spaces = b-count+a.get(i).length();
                String justified_line = adjustSpaces(a.subList(left_indx,i-1),spaces);
                result_list.add(justified_line);
                left_indx = i;
                count = 0;
            }
        }
        return result_list;
    }

    private static String adjustSpaces(List<String> strings, int spaces) {
        int in_between = spaces/(strings.size()-1);
        int at_last = spaces%(strings.size()-1);
        StringBuilder result = new StringBuilder();
        String even_spaces = getSpaces(in_between);
        String odd_spaces = getSpaces(at_last);
        for (int i = 0; i < strings.size(); i++) {
            if(i==0){
                result = new StringBuilder(strings.get(0));
            }else if(i == strings.size()-1){
                if(at_last == 0){
                    result = new StringBuilder(result.toString()+ even_spaces +  strings.get(i));
                }else {
                    result = new StringBuilder(result.toString()+ odd_spaces +  strings.get(i));
                }
            }else{
                result = new StringBuilder(result.toString()+ even_spaces + strings.get(i));
            }
        }
        return result.toString();

    }

    private static String getSpaces(int in_between) {
        String result = "";
        for (int i = 0; i < in_between ; i++) {
            result = result+ " ";
        }
        return result;
    }


    /*------------------------------------End of Justified text------------------------------------------------------*/

    /*-----------------------------Multiply strings--------------------------------------------*/
    public static String multiply(String a, String b) {
        int size = a.length();
        ArrayList<String> result_list = new ArrayList<>();
        if(a.equals("0")||b.equals("0")) return "0";
        int min_len = a.length()>b.length()? b.length():a.length();
        String longer = a.length()>b.length()? a:b;
        String shorter = a.length()>b.length()? b:a;
        for (int i = 0; i < min_len; i++) {
            String zeros = setZeros(i);
            result_list.add(multiply(longer,shorter.charAt(min_len-i-1))+ zeros);
        }
        StringBuilder sum = new StringBuilder(result_list.get(0));
        for (int i = 1; i <result_list.size() ; i++) {
            sum = new StringBuilder(addition(sum.toString(),result_list.get(i)));
        }

        int first = 0;
        for (int i =0;i <sum.length();i++){
            if(sum.charAt(i) != '0'){
                first = i;
                break;
            }
        }

        return sum.substring(first).toString();
    }

    private static String setZeros(int i) {
        String zeros = "";
        for (int j = 0; j < i ; j++) {
            zeros+="0";
        }
        return zeros;
    }

    public static String multiply(String m, char n){
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = m.length()-1; i >=0 ; i--) {
            int product = Character.getNumericValue(m.charAt(i)) * Character.getNumericValue(n);
            result.append((product+carry)%10);
            carry = (product+carry)/10;
        }
        if(carry != 0) result.append(carry);
        return  result.reverse().toString();
    }

    public static String addition(String a,String b){
        StringBuilder result = new StringBuilder();
        int max_len = a.length()>b.length()? a.length():b.length();
        String longer = a.length()>b.length()? a:b;
        String shorter = a.length()>b.length()? b:a;
        int carry = 0;
        for (int i = max_len-1; i >=0 ; i--) {
            if(i>=max_len-shorter.length()){
                int top = Character.getNumericValue(longer.charAt(i));
                int lower = Character.getNumericValue(shorter.charAt(i-max_len+shorter.length()));
                result.append((top+lower + carry)%10);
                carry = (top+lower+carry)/10;
            }else{
                int top = Character.getNumericValue(longer.charAt(i));
                result.append((top + carry)%10);
                carry = (top+carry)/10;
            }
        }
        if(carry != 0) result.append(carry);
        return result.reverse().toString();
    }

    /*------------------------------end of multiply two strings-------------------------------------------*/


    /*------------------Implementation of StrStr-------------------------------*/
    public static int strStr(final String haystack, final String needle) {
        int haystack_size = haystack.length();
        int needle_size = needle.length();

        if(haystack_size != 0 && needle_size != 0 && haystack_size >= needle_size){
            for (int low = 0; low+needle_size <= haystack_size;low++){
                if(haystack.charAt(low) == needle.charAt(0)){
                    if(haystack.substring(low,low+needle_size).equals(needle)) {
                        return low;
                    }
                }
            }
            return -1;

        }else{
            return -1;
        }
    }
    /*--------------------end of StrStr-----------------------------*/

    /*------------------count and say--------------*/
    public static String countAndSay(int a) {
        StringBuilder p_number = new StringBuilder("1");
        StringBuilder number = new StringBuilder();
        if(a > 1) {
            for (int i = 1; i < a; i++) {
                number = new StringBuilder();
                for (int j = 0; j < p_number.length(); j++) {
                    int digit = Character.getNumericValue(p_number.charAt(j));
                    int pointer = j + 1, count = 1;
                    while (pointer < p_number.length() && Character.getNumericValue(p_number.charAt(pointer)) == digit) {
                        count++;
                        pointer++;
                    }
                    number.append(count);
                    number.append(digit);
                    j = j + count - 1;
                }
                p_number = number;
            }
        }else{
            return p_number.toString();
        }
        return number.toString();
    }
    /*------------------end count and say--------------*/

    /*------------------------ZIG ZAG Printing ----------------------------------*/
    public static String convert(String a, int high) {
        int low = 1,step =0,turn,index = 0;
        StringBuilder result = new StringBuilder();

        if(a.length()<= high || high == 1){
            return a;
        }

        for (int level = high; level>=low;level--){
            turn = 1;
            step = index;
            result.append(a.charAt(step));
            while (step < a.length()){
                if (turn % 2 == 1) {
                    if(level == low){
                        turn ++;
                        continue;
                    }
                    step += 2 * (level - low);
                } else {
                    if(high == level){
                        turn++;
                        continue;
                    }
                    step += 2 * (high - level);
                }
                if(step < a.length()) result.append(a.charAt(step));
                turn++;
            }
            index ++;
        }
        return result.toString();
    }

    /*------------------------End of ZIG ZAG printing----------------------------------*/

    /*---------------------Adding Binary numbers --------------*/
    public  static String addBinary(String a, String b) {
        int size_a = a.length();
        int size_b = b.length();
        StringBuilder str_a = new StringBuilder(a).reverse();
        StringBuilder str_b = new StringBuilder(b).reverse();

        StringBuilder result = new StringBuilder();
        int iterator = -1,m,n,k;
        String longer,shorter;

        if(size_a > size_b){
            iterator = size_a;
            longer = str_a.toString();
            shorter = str_b.toString();
        }else{
            iterator = size_b;
            longer = str_b.toString();
            shorter = str_a.toString();
        }
        int carry = 0;

        for (int i = 0; i < iterator; i++) {

            if(i < shorter.length() ){
                m = Character.getNumericValue(str_a.charAt(i));
                n = Character.getNumericValue(str_b.charAt(i));
                switch (m+n+carry){
                    case 0:
                        result.append(0);
                        carry = 0;
                        break;
                    case 1:
                        result.append(1);
                        carry = 0;
                        break;
                    case 2:
                        result.append(0);
                        carry = 1;
                        break;
                    case 3:
                        result.append(1);
                        carry = 1;
                        break;
                }

            }else{
                k = Character.getNumericValue(longer.charAt(i));
                if(carry + k == 2){
                    result.append(0);
                    carry = 1;
                }else if(carry + k == 1){
                    result.append(1);
                    carry = 0;
                }else {
                    result.append(0);
                    carry = 0;
                }
            }
        }
        if(carry == 1) result.append(1);
        return result.reverse().toString();
    }
    /*---------------------end Adding Binary numbers --------------*/

    /*------------------------reverse a string by words ------------------------*/

    public static String reverseWords(String a) {
        StringBuilder reverse_str = new StringBuilder();
        StringBuilder words = new StringBuilder();
        int low = 0;
        int high = a.length()-1;

        while (!(a.charAt(low) != ' ' && a.charAt(high) != ' ')){
            if(a.charAt(low) == ' ')low ++;
            if(a.charAt(high) == ' ') high --;
        }

        while (high >= low){
            if(a.charAt(high) != ' '){
                words.append(a.charAt(high));
                high--;
            }else{
                reverse_str.append(words.reverse());
                words = new StringBuilder();
                reverse_str.append(' ');
                while(a.charAt(high)== ' ') high--;
            }
        }
        reverse_str.append(words.reverse());
        return reverse_str.toString();
    }

    /*------------------------end of reverse a string by words ------------------------*/

    /*------------------------reverse a string------------------------*/
    public String reverseString(String a) {
        StringBuilder reverse_str = new StringBuilder();
        int low = 0;
        int high = a.length()-1;
        while (!(a.charAt(low) != ' ' && a.charAt(high) != ' ')){
            if(a.charAt(low) == ' ')low ++;
            if(a.charAt(high) == ' ') high --;
        }

        while (high >= low){
            if(a.charAt(high) != ' '){
                reverse_str.append(a.charAt(high));
                high--;
            }else{
                reverse_str.append(' ');
                while(a.charAt(high)== ' ') high--;
            }
        }
        return reverse_str.toString();

    }

    /*------------------------end of reverse a string------------------------*/

    /*------------------------Convert string to integer and reverse ----------------*/

    public static double convertStrToInt(String a){
        int high = a.length()-1;
        int low = 0;
        boolean is_neg = false;
        int size = a.length()-1;
        int digit;
        int decimal_pos = -1;
        int result = 0;
        if(!Character.isDigit(a.charAt(0))){
            low = 1;
            is_neg = true;
            size--;
        }
        while (high>=low){
            if(Character.isDigit(a.charAt(high))){
                digit = Character.getNumericValue(a.charAt(high));
                result += (digit)*((int)Math.pow(10,size));
                size--;
                high --;

            }else {
                decimal_pos = high - low +1;
                high --;
            }
        }
        if(is_neg) result = -1*result;
        if(decimal_pos != -1){
            return result/Math.pow(10,decimal_pos);
        }

        return result;
    }
    /*------------------------end COnvert string to integer and reverse----------------*/


    /*---------------------Longest palindrome in a string----------------------*/

    public static String longestPalindromeSubStr(String s) {
        int longest = 1;
        StringBuilder longest_palindrome = new StringBuilder();
        int size = s.length();
        int is_palindrome = -1;
        int sub_str_size = 0;
        if (isPalindrome(s) == 1) {

            return s;
        }
        for(int low = 0; low < s.length(); low ++){
            for(int present =  size-1; present> low + longest-1; present --){
                sub_str_size = s.substring(low,present+1).length();
                is_palindrome = isPalindrome(s.substring(low,present+1));
                if(is_palindrome == 1){
                    if(longest < sub_str_size) {
                        longest = sub_str_size;
                        longest_palindrome = new StringBuilder(s.substring(low,present+1));
                    }
                    break;
                }
                if(low + longest > size){
                    break;
                }
            }
        }
        if(longest_palindrome.length() == 0) {
            return Character.toString(s.charAt(0));
        }
        return longest_palindrome.toString();

    }
    /*--------------------- end of Longest palindrome in a string----------------------*/

    /*---------------------------------- Pretty JSON -------------------------------------------------------*/


    public static ArrayList<String> prettyJSON(String a) {
        int indents = 0;
        ArrayList<String> brackets = new ArrayList<>();
        HashMap brackets_mapping = new HashMap();
        brackets_mapping.put("{","}");
        brackets_mapping.put("[","]");
        ArrayList<String> result = new ArrayList<>();
        StringBuilder element = new StringBuilder();
        boolean is_brack = true;

        for (int i = 0; i < a.length() ; i++) {
            String indent_str = setIndentation(brackets.size());
            switch (a.charAt(i)){
                case '{':
                    result.add(indent_str.concat("{"));
                    brackets.add("{");
                    break;
                case '[':
                    result.add(indent_str.concat("["));
                    brackets.add("[");
                    break;
                case '}':
                    brackets.remove(brackets.size()-1);
                    indent_str = setIndentation(brackets.size());
                    if(a.charAt(i+1) == ',') {
                        result.add(indent_str.concat("},"));
                        i++;
                    }else{
                        result.add(indent_str.concat("}"));
                    }
                    break;
                case ']':
                    brackets.remove(brackets.size()-1);
                    indent_str = setIndentation(brackets.size());
                    if(a.charAt(i+1) == ',') {
                        result.add(indent_str.concat("],"));
                        i++;
                    }else{
                        result.add(indent_str.concat("]"));
                    }
                    break;
                default:
                    if(!Character.toString(a.charAt(i)).equals(" ")) {
                        element = new StringBuilder();
                        element.append(indent_str);
                        while (a.charAt(i) != ',' && a.charAt(i) != '}' && a.charAt(i) != ']'&& a.charAt(i) != '[' && a.charAt(i) != '{' && i<a.length()) {
                            element.append(a.charAt(i));
                            i++;
                        }
                        if(a.charAt(i) == ',') element.append(",");
                        else i --;
                        result.add(element.toString());
                        break;
                    }else break;
            }
        }

        return result;
    }

    private static String setIndentation(int size) {
        String result = "";
        for (int i = 0; i < size ; i++) {
            result +="\t";
        }
        return result;
    }

    /*-----------------------------------End of Pretty JSON------------------------------------------------------*/


    /*------------------------------------longest Common Prefix in a list-----------------------------------------------------*/
    public static String longestCommonPrefix(List<String> a) {
        StringBuffer longest_common_prefix = new StringBuffer();
        String first_word = a.get(0);
        int total = a.size();
        char at_pos_i;
        for (int i = 0; i < first_word.length() ; i++) {
            at_pos_i = first_word.charAt(i);
            int count = 0;
            try{
                for (String words : a) {
                    if(words.charAt(i) == at_pos_i){
                        count ++;
                    }else{
                        break;
                    }
                }
            }catch (StringIndexOutOfBoundsException e){
                break;
            }
            if(count == total) longest_common_prefix.append(at_pos_i);
            if(!longest_common_prefix.toString().equals(first_word.substring(0,i+1))){
                break;
            }
        }

        return longest_common_prefix.toString();

    }

    /*----------------------------------end of longestCommonPrefix-------------------------------------------------------*/

    /*----------------------------------Power of 2-------------------------------------------------------*/


    public static int power(String a) {
        StringBuffer inter_result = new StringBuffer(a);
        if(a.equals("1")) return 0;
        while (Character.getNumericValue(inter_result.charAt(inter_result.length()-1))%2==0){
            inter_result = divideByTwo(inter_result);
        }
        if(inter_result.length()>1) return 0;
        if(Integer.parseInt(inter_result.toString()) == 1)return 1;
        return 0;
    }

    private static StringBuffer divideByTwo(StringBuffer num) {
        StringBuffer result = new StringBuffer();
        int carry = 0,digit;
        for (int i = 0; i < num.length() ; i++) {
            StringBuffer dividend = new StringBuffer();
            dividend.append(carry);
            dividend.append(num.charAt(i));
            digit = Integer.parseInt(dividend.toString());

            while (digit <2 && i <num.length()-1){
                i= i+1;
                dividend.append(num.charAt(i));
                digit = Integer.parseInt(dividend.toString());
            }
            result.append(digit/2);
            carry = digit%2;
        }

        return result;
    }

    /*------------------------end of power of 2-------------------------------------*/

    /*----------length of last word in the sentence string-------*/
    public static int lengthOfLastWord(final String a){
        int last_word = 0;
        int low = 0;
        int high = a.length()-1;

        while(low <= high){
            if(last_word == 0 && a.charAt(high) == ' ') {
                high --;
                continue;
            }
            if(a.charAt(high) != ' '){
                last_word ++;
                high --;
            }else{
                break;
            }
        }
        return  last_word;
    }
    /*---------- end length of last word in the sentence string-------*/

    /*----------get longest word in the sentence string-------*/
    public static int lengthOfLongestWord(final String a) throws ArrayIndexOutOfBoundsException{
        int longest_string = 0;
        int sub_string = 0;
        try{
            int low = 0,high = a.length();
            int index;
            while (low + longest_string < high &&low < high){
                index = checkForEmptySpaces(a.substring(low,high));
                if(index > longest_string) longest_string = index;
                if(index == -1 && a.substring(low,high).length() > longest_string && a.substring(low,high).split( " ").length > 0) {
                    longest_string = a.substring(low,high).length();
                }
                if(index >= 0) low += index + 1;
                else low++;

            }
            return longest_string;
        }catch (StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return longest_string;
    }

    public static int checkForEmptySpaces(String s){
        int isEmpty = -1;
        if(s.split(" ").length == 0){
            isEmpty = -1;
        }else {
            if(s.split(" ").length > 0) isEmpty = s.split(" ")[0].length();
        };
        return isEmpty;
    }
    /*----------end of get longest word in the sentence string-------*/

    /*----------palindrom string-------*/

    public static int isPalindrome(String a) throws NullPointerException, StringIndexOutOfBoundsException{

        int is_palindrome = 1;
        try {


            int low = 0, high = a.length() - 1;

            if(low == high) return is_palindrome;

            while (low <= high) {
                while (!Character.isAlphabetic(a.charAt(low)) && !Character.isDigit(a.charAt(low)) && low < high) low++;
                while (!Character.isAlphabetic(a.charAt(high)) && !Character.isDigit(a.charAt(high)) && high > low) high--;

                if (Character.toLowerCase(a.charAt(low)) != Character.toLowerCase(a.charAt(high))) {
                    is_palindrome = 0;
                    break;
                }
                low++;
                high--;
            }

        }catch (NullPointerException e){
            System.out.println("Got a null value");
            return 0;
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("check for low and high values");
            return 0;

        }
        return is_palindrome;
    }

    /*----------end palindrom string-------*/

    /*-------------------------- minimum characters to make it a palindrome --------------------------*/
    public static int solve(String A){
        try {
            int size = A.length();
            int last_occurrance = size-1;
            int inter_size = 0;
            StringBuffer sub_string_a = new StringBuffer(A);
            int is_palindrome = isPalindrome(A);
            char first_letter = sub_string_a.charAt(0);
            int count = 0;
            while (is_palindrome != 1){
                inter_size = sub_string_a.substring(0,last_occurrance+1).length();
                last_occurrance = checkPositionFromEnd(sub_string_a.substring(0,last_occurrance+1),first_letter);
                if(last_occurrance == 0 && count == 0){
                    return size -2;
                }else if(last_occurrance == 0 && count != 0){
                    break;
                }
                count += inter_size - last_occurrance - 1;
                is_palindrome = isPalindrome(sub_string_a.substring(0,last_occurrance+1).toString());
                if(is_palindrome != 1) {
                    last_occurrance --;
                    count++;
                }

            }
            return count;

        }catch (NullPointerException e){

        }
        return 0;

    }

    public static int checkPositionFromEnd(String s, char n){
        try{
            int size = s.length();
            int high = size -1;
            while (high != -1){
                if(s.charAt(high) != n){
                    high --;
                }else{
                    return high;
                }
            }
            return high;
        }catch (NullPointerException e){
            e.printStackTrace();
            return -1;
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("check low and high indexes");
            return -1;
        }

    }
    /*-------------------------- end minimum characters to make it a palindrome --------------------------*/

}
