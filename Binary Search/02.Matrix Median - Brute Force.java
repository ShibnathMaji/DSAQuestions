import java.util.*;
public final class Solution 
{
    public static int findMedian(int matrix[][], int m, int n) 
    {
        int[] numbers = new int[m*n];
        int idx = 0;
        for(int i = 0; i<m; i++)
        {
            for(int j = 0; j<n; j++)
            {
                numbers[idx++] = matrix[i][j];
            }
        }
        //Sort list
        Arrays.sort(numbers);

        int median_idx = (int) (numbers.length/2);
        int median = numbers[median_idx];

        return median;

    }
}