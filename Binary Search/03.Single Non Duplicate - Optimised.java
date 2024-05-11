/*
Intution: 
a. Given array length will always be odd. 
    -> In 0-based indexing, it'll always have even index for the last element. 
    -> Non-duplicate integer will always be in an even index. 
b. Under normal circumstance:
    -> If index = EVEN, duplicate element will be on the RIGHT.
    -> If index = ODD, duplicate element will be on LEFT.
    -> The above 2 conditions REVERSE only if a non duplicate element is present 
       somewhere on the left.
    We use this property to search for the non-duplicate element. (Binary Search)
*/
class Solution 
{
    public int singleNonDuplicate(int[] nums) 
    {
        int low = 0, high = nums.length-2;
        while(low<=high)
        {
            int mid = (high+low)/2;
            
            /* 
            If we perform XOR 1 with an even number, it's value increases by 1.
            Similarly, perform XOR 1 with an even number, it's value decreases by 1.
            By XOR 1, we check the left or right index, depending whether mid is odd or even.
            */
            if(nums[mid] == nums[mid^1])
                low = mid+1;
            else
                high = mid-1;
        }
        return nums[low];
    }
}
/*
 * Time Complexity: O(log n) 
 * Since we use Binary Search to traverse the entire array. Time Complexity of Binary Search = O(log n)
 * Space Complexity: O(1)
 * Since we do not need any additional space.
 */