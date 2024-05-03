/*
Intution: 
a. Here we need to check three conditions before placing a number in the empty slots:
    -> That row must not have the number we are to place
    -> That column must not have the number we are to place
    -> The 3x3 square grid inside which the cell belongs, must not hav the number either.

b. We iterate through the given matrix and we have a choice of number from 1 to 9 to place in each of the empty cells we get.
    -> BEFORE placing the number, we check if the above mentioned criterias. 
    -> If they are true, only then we place the number and proceed with the recursion and return true.

c. The Base case here is done via the iteration above. The iteration of the entire matrix only happens if "true" is returned at every stage.
    -> If at point the recursion returns false, we backtrack and empty the cell.
*/
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