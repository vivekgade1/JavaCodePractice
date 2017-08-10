package Twitter;

import java.util.*;

public class Solution {
    public int maxLength(int[] a, int k){
     int total = 0;
     int result = 0;
     Arrays.sort(a);
        for (int item: a) {
            total += item;
            if(total > k){
                break;
            }
            result++;
        }
     return result;
    }

    public int krakenCount(int m, int n){
        int result = 0;

        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <=n ; j++) {
                if(j==n && i == m){
                    continue;
                }
                int _i = i+1;
                int _j = j+1;
                if(_i <=m && j <= n) result += 1;
                if(_j <=n && i <=m) result += 1;
                if(_i <=m && _j <=n) result += 1;
                if(i == 1 && j ==1){
                    continue;
                }else{
                    result --;
                }

            }
        }

        return result;


    }
}
