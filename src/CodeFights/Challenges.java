package CodeFights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Challenges {
    public static void main(String[] args){
        System.out.println(cheapMarquee("Come get your Pan Galactic Gargle Blaster... at The Restaurant at the end of the universe!!",42));
    }
    static int cheapMarquee(String message, int width) {
        HashMap<String,int[]> alpha_map = new HashMap<>();
        int result = 0;
        createMap(alpha_map); // intializes the hashmap
        int left = 0, right = 0;
        ArrayList<Integer> message_arr = new ArrayList<>(); // maintain the series of integers which occur after converting the message
        int[] map_arr;
        for (int i = 0; i < message.length(); i++) {
            map_arr = alpha_map.get(message.substring(i,i+1).toUpperCase());
            if(map_arr != null){
                for (int j = 0; j < map_arr.length ; j++) {
                    message_arr.add(map_arr[j]);
                }
                if(i != message_arr.size()-1)message_arr.add(0);
            }

        }


        int[] sum_arr = new int[message_arr.size()]; // this is to maintain the dp sum
        int sum = 0;
        while (right < message_arr.size()){
            if(right == 0) {
                sum_arr[right] = message_arr.get(right);
                sum = sum_arr[right];
                right++;
                continue;
            }
            sum_arr[right] = sum_arr[right-1] + message_arr.get(right);
            if(right< width){
                sum = sum_arr[right];
            }else{
                sum = sum_arr[right] - sum_arr[right-width];
                left++;
            }
            if(sum > result) result = sum;
            right++;
        }

        return result;

    }
    // intiatlization of the pixel font for 4x5
    private static void createMap(HashMap<String, int[]> alpha_map) {
        //String input_range = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?";
        alpha_map.put("A",new int[]{4,2,2,4});
        alpha_map.put("B",new int[]{5,3,3,2});
        alpha_map.put("C",new int[]{3,2,2,2});
        alpha_map.put("D",new int[]{5,2,2,3});
        alpha_map.put("E",new int[]{5,3,3,2});
        alpha_map.put("F",new int[]{5,2,2,1});
        alpha_map.put("G",new int[]{3,2,3,3});
        alpha_map.put("H",new int[]{5,1,1,5});
        alpha_map.put("I",new int[]{2,5,2,2});
        alpha_map.put("J",new int[]{2,2,4,1});
        alpha_map.put("K",new int[]{5,1,2,2});
        alpha_map.put("L",new int[]{5,1,1,1});
        alpha_map.put("M",new int[]{5,2,2,5});
        alpha_map.put("N",new int[]{5,1,2,5});
        alpha_map.put("O",new int[]{3,2,2,3});
        alpha_map.put("P",new int[]{5,2,2,1});
        alpha_map.put("Q",new int[]{3,2,3,4});
        alpha_map.put("R",new int[]{5,3,3,2});
        alpha_map.put("S",new int[]{2,3,3,2});
        alpha_map.put("T",new int[]{1,5,1,1});
        alpha_map.put("U",new int[]{4,1,1,4});
        alpha_map.put("V",new int[]{4,1,2,2});
        alpha_map.put("W",new int[]{4,2,2,5});
        alpha_map.put("X",new int[]{3,2,2,3});
        alpha_map.put("Y",new int[]{2,3,1,1});
        alpha_map.put("Z",new int[]{3,3,3,2});
        alpha_map.put("0",new int[]{3,4,4,3});
        alpha_map.put("1",new int[]{2,5,1,1});
        alpha_map.put("2",new int[]{2,3,3,2});
        alpha_map.put("3",new int[]{3,3,3,2});
        alpha_map.put("4",new int[]{2,2,5,1});
        alpha_map.put("5",new int[]{4,3,3,2});
        alpha_map.put("6",new int[]{3,3,3,2});
        alpha_map.put("7",new int[]{2,2,2,2});
        alpha_map.put("8",new int[]{2,3,3,2});
        alpha_map.put("9",new int[]{2,3,3,3});
        alpha_map.put(".",new int[]{1});
        alpha_map.put(",",new int[]{2});
        alpha_map.put("!",new int[]{4});
        alpha_map.put("?",new int[]{1,3,2,1});
        alpha_map.put(" ",new int[]{0,0});

    }

}
