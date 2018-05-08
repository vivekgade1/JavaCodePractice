import java.util.*;
public class CrackingTheCoding {
    
    public static void main(String[] args){
        CaseIn c = new CaseIn("Hello");
        System.out.println(c.equals("HELLO") + " "+ "HELLO".equals(c));

    }

    private static class CaseIn{
        private final String s;

        public CaseIn(String s){
            this.s = s;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof CaseIn){
                return ((CaseIn)o).s.equalsIgnoreCase(s);
            }

            if(o instanceof String){
                return ((String)o).equalsIgnoreCase(s);
            }
            return false;
        }
    }

    public static boolean canMakeWall(int availableSmall, int availableLarge, int goalWallLength) {
        final int ssize = 1; // constant small brick size
        final int bsize = 5; // constant large brick size

        int total_large = availableLarge*bsize;

        if(total_large != 0 && goalWallLength%total_large <= availableSmall){
            return true;
        }else if(total_large == 0 && goalWallLength <= availableSmall){
            return true;
        }else{
            return false;
        }


    }



}
