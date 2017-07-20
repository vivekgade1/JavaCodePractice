package interviewBit.Numbers;

/**
 * Created by vgade on 7/20/17.
 */
public class StringProblems {
    public static void main(String[] args){
        System.out.println(isPalindrome(null));

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
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("check for low and high values");

        }
        return is_palindrome;
    }

    /*----------end palindrom string-------*/

    /*-------------------------- minimum characters to make it a palindrome --------------------------*/

    
    /*-------------------------- end minimum characters to make it a palindrome --------------------------*/
}
