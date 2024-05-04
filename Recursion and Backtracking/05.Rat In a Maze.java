/*
Intution: 
Here, we check each of the 4 available directions (D,L,R,U -> Arranged lexicographically) everytime we proceed.
If we find the cell value as 1, we can proceed to the cell. 
Additionally, once we proceed, we mark the current cell by 99 (since 99 is outside the range of values in the matrix)
This is to make sure we don't come back to the same cell again.

The base case is triggered when we reach the index : (n-1,n-1)
*/
// m is the given matrix and n is the order of matrix
class Solution 
{
    static void findPathRecursion(int[][] m, String path, ArrayList<String> listOfPaths,
                                        int i, int j)
    {
        // Base Case
        if(i==m.length-1 && j == m[0].length-1)
        {
            listOfPaths.add(path);
            return;
        }
        
        int temp = m[i][j];
        // Down
        if(i+1<m.length && m[i+1][j]!=0 && m[i+1][j]==1)
        {
            m[i][j]=99;
            findPathRecursion(m, path+"D", listOfPaths, i+1, j);
            m[i][j]=temp;
        }
        
        //Left
        if(j-1>=0 && m[i][j-1]!=0 && m[i][j-1]==1)
        {
            m[i][j]=99;
            findPathRecursion(m, path+"L", listOfPaths, i, j-1);
            m[i][j]=temp;
        }
        
        //Right
        if(j+1<m[0].length && m[i][j+1]!=0 && m[i][j+1]!=99)
        {
            m[i][j]=99;
            findPathRecursion(m, path+"R", listOfPaths, i, j+1);
            m[i][j]=temp;
        }
        
        //Upward
        if(i-1>=0 && m[i-1][j]!=0 && m[i-1][j]!=99)
        {
            m[i][j]=99;
            findPathRecursion(m, path+"U", listOfPaths, i-1, j);
            m[i][j]=temp;
        }
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) 
    {
        String path = "";
        ArrayList<String> listOfPaths = new ArrayList<>();
        
        if(m[0][0]==1) //Entry point is (0,0). We proceed with the problem only if the entry point is marked as 1.
            findPathRecursion(m, path, listOfPaths, 0, 0);
        
        return listOfPaths;
    }
}