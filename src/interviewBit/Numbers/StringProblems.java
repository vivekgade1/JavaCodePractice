package interviewBit.Numbers;

/**
 * Created by vgade on 7/20/17.
 */
public class StringProblems {
    public static void main(String[] args){
        System.out.println(checkPositionFromEnd("",'k'));

    }
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
    public int solve(String A){
            try {
                int size = A.length();
                int end = size-1;
                int last_occurrance;
                StringBuffer sub_string_a = new StringBuffer(A);
                while(isPalindrome(sub_string_a.toString()) == 0){
                    last_occurrance = checkPositionFromEnd(sub_string_a.toString(),A.charAt(end));
                    sub_string_a = (StringBuffer) sub_string_a.subSequence(0,last_occurrance+1);
                }

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
