class Solution 
{
    static boolean canBePlaced(char[][] board, int i, int j, char ch)
    {
        //Row check
        for(int col = 0; col<9; col++)
        {
            if(board[i][col]==ch)
                return false;
        }
        //Column Check
        for(int row = 0; row<9; row++)
        {
            if(board[row][j]==ch)
                return false;
        }
        //3x3 square check
        int squareIdxRow = (int) i/3; int squareIdxCol = (int) j/3;
        int startIdxRow = squareIdxRow*3; int startIdxCol = squareIdxCol*3;
        for(int row = startIdxRow; row<= startIdxRow+2; row++ )
        {
            for(int col = startIdxCol; col<= startIdxCol+2; col++)
            {
                if(board[row][col]==ch)
                    return false;
            }
        }
        
        return true;
    }
    public boolean sudokuRecursion(char board[][])
    {
        for(int i = 0; i<9; i++)
        {
            for(int j = 0; j<9; j++)
            {
                if(board[i][j]=='.') // Choosing only the empty cells
                {
                    for(char ch = '1'; ch<='9'; ch++)
                    {
                        if(canBePlaced(board, i, j, ch))
                        {
                            board[i][j]=ch;
                        
                            if(sudokuRecursion(board)==true)
                                return true;
                            else
                                board[i][j]='.';
                        }   
                    }
                    return false;
                } 
            }
        }
        return true;
    }
    public void solveSudoku(char[][] board) 
    {
        sudokuRecursion(board);
    }
}