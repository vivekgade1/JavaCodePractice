package interviewBit;

public class Graphs {

    public static void main(String[] args){
        char[][] input = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        surroundingRegions(input);

    }

    public static void surroundingRegions(char[][] board) {
        int row = board.length;
        int col;
        if(row == 0) return;
        else {
            col = board[0].length;
            if(col == 0) return;
        }

        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {
                if(board[i][j] == 'O'){
                    if((board[i-1][j] == 'O' || board[i-1][j] == 'X') &&
                            (board[i+1][j] == 'O' || board[i+1][j] == 'X') &&
                            (board[i][j-1] == 'O' || board[i][j-1] == 'X') &&
                            (board[i][j+1] == 'O' || board[i][j+1] == 'X')){
                        board[i][j] = 'X';
                    }

                }
            }
        }
        System.out.println(board);

    }
}
