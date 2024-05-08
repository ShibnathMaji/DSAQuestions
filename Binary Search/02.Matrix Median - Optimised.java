/*
a. It is mentioned that in the given matrix R and C will always be odd (number of rows and columns).
	-> So, the number of elements in the matrix will be R*C will also be always ODD.
	-> Also mentioned, each row is sorted
b. Given, a SORTED list of R*C elements, 
	-> Median's index = Integer value of (R*C)/2
	-> Median will always have same number of elements on it's left and right and that is, (R*C)/2.
	-> Elements on left will be <= Median. Elements on right will be >= Median.

c. So it can be said, number of elements in the matrix which are lesser or equal to median, must be greater than (R*C)/2.
	-> There can be many such elements in the matrix which satisfies the above condition. 
	-> The median will be the first element in the sorted matrix, which satisfies the above condition.
d. To find out the number:
	-> We first find the searchspace i.e., min and max (Smallest and largest elements in the array) and the midpoint.
	   Note: This mid point might not be an element in the matrix
	-> Next, we find the number of elements in the matrix, that are smaller or equal to midpoint.
			We find this by searching for Upper Bound on EACH row, with our target set as the midpoint.
			Upper Bound is that INDEX in an array where the eleement is greater than the target. 
			Since every row is sorted, and indexing is 0 based, calculating the Upper Bound also gives the number of elements smaller or equal to target (midpoint)
	-> Once we get the number of elements for current midpoint, we compare it with (R*C)/2
			If number of elements <= (R*C)/2, we reduce our search space to the right half.
			Similarly, if number of elements > (R*C)/2, we reduce our search space to the left half.
	   This conitnues, till max crosses min and min stores the required answer.
*/ 
class Solution 
{
    static int upperBound(int row[], int target, int C)
    {
        // Returns the index (Upper Bound)
        int low = 0, high = C-1;
        int answer = C;
        while(high>=low)
        {
            int mid = (high+low)/2;
            
            if(row[mid]>target)
            {
                answer = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return answer;
    }
    static int countNoOfElements(int R, int C, int target, int matrix[][])
    {
        // We will check for upper bound for the target given, in each row of the matrix 
        int count = 0;
        for(int i = 0; i<R; i++)
        {
            count+=upperBound(matrix[i], target, C);
        }
        return count;
    }
    static int median(int matrix[][], int R, int C) 
    {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // Calculating the search space
        for(int i = 0; i<R; i++)
        {
            // Rows of given matrix are sorted. Hence, we need to compare only the first and last elements
            min = Math.min(min, matrix[i][0]);
            max = Math.max(max, matrix[i][C-1]);
        }
        
        int mid = 0; int countElements = 0; 
        
        int req = R*C/2; //-> No. of elements that on the left of median
        /*
        We find median in a SORTED group of elements.
        Elements to the left of the median are smaller or equal to the median.
        Similarly, elements on the right of median are greater or equal to the median.
        TL;DR: Median is that element which has (R*C)/2 elements to its left.
        */
        while(max>=min)
        {
            mid = (min+max)/2;
            // Find number of elements which are lesser or equal to mid.
            countElements = countNoOfElements(R, C, mid, matrix); 
            
            /*
            We need to find the first element where, number of elements that are lesser or equal to median,
            is greater than (row*column)/2
            */ 
            if(countElements <= R*C/2)
                min  = mid +1;
            else
                max = mid -1;
        }
        return min;
    }
}

/*
Time Complexity:
Given constraints:
1 <= R, C <= 400
1 <= matrix[i][j] <= 2000

So, searchspace at max can be 2000 i.e., log (2000)
For each row we are calling the Binary search function is called. Each row has C number of elements i.e.,  R * log C
Hence total time complexity: O(log (2000) * R * log C)
*/