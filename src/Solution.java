import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class Solution {

    public static void main(String [] args){
        int [] x = {1,2,3};
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : x) {
            l.add(i);
        }
        System.out.println(subUnsort(l));
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
}
