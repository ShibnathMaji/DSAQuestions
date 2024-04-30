/*
Intution: 
a. Here, we traverse through the n x n matrix array, row wise checking if the partiular index is available or not. 
	-> For checking if it is safe or not, we check vertically horizontally and diagonally for the presence of another queen.
	-> If there's no queen on the above mentioned positions, we place the Queen there and increment the row in the next recursion call.
b. Base Case is triggered when col = n
	-> Once triggered, we add the row with the queen position to the List of List.
	-> Upon returning, we replace the queen with a dot.
*/
class Solution 
{
    static void addChessBoard(char chessBoard[][], List<List<String>> allChessboard)
    {
        String s = "";
        List<String> currChessBoard = new ArrayList<>();  
        for(int i = 0; i<chessBoard.length; i++)
        {
            s = "";
            for(int j = 0; j<chessBoard.length; j++)
            {
                if(chessBoard[i][j]=='Q')
                    s+= chessBoard[i][j];
                else
                    s+='.';
            }
            currChessBoard.add(s);
        }
        allChessboard.add(new ArrayList<>(currChessBoard));
    }
    static boolean isSafe(int row, int col, char chessBoard[][])
    {
        // Up & Down -> Vertical
        int i = chessBoard.length-1;
        while(i>=0)
        {
            if(chessBoard[i--][col]=='Q')
                return false;
        }
        // Left & Right -> Horizontal
        int j = chessBoard.length-1;
        while(j>=0)
        {
            if(chessBoard[row][j--]=='Q')
                return false;
        }
        
        // Diagonal - Up Left
        i = row; j = col;
        for(i = row; i>=0 && j>=0; i--, j--)
        {
            if(chessBoard[i][j]=='Q')
                return false;
        }
        
        // Diagonal - Up Right
        i = row; j = col;
        for(i = row; i>=0 && j<chessBoard.length; i--, j++)
        {
            if(chessBoard[i][j]=='Q')
                return false;
        }
            
        // Diagonal - Bottom Left
        i = row; j = col;
        for(i = row; i<chessBoard.length && j>=0; i++, j--)
        {
            if(chessBoard[i][j]=='Q')
                return false;
        }
        
        // Diagonal - Bottom Right
        i = row; j = col;
        for(i = row; i<chessBoard.length && j<chessBoard.length; i++, j++)
        {
            if(chessBoard[i][j]=='Q')
                return false;
        }
        
        return true;
    }
    static void NQueensRecursion(int col, char chessBoard[][], 
                                 List<List<String>> allChessboard)
    {
        //Base Case
        if(col == chessBoard.length)
        {
            addChessBoard(chessBoard, allChessboard);
            return;
        }
        
        for(int row = 0; row < chessBoard.length; row++)
        {
            if(isSafe(row, col, chessBoard)==true)
            {
                chessBoard[row][col]='Q';
                NQueensRecursion(col+1, chessBoard, allChessboard);
                
                chessBoard[row][col]='.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) 
    {
        List<List<String>> allChessboard = new ArrayList<>();
        char chessBoard[][] = new char[n][n];
        
        NQueensRecursion(0, chessBoard, allChessboard);
        return allChessboard;
    }
}