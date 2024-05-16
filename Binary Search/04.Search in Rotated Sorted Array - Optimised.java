/*
Intution: 
We are given an array which is sorted and then pivoted at a certain index. Need to search for a given target value and return it's index.
Mentioned that all elements will be unique
a. Sorted array and Searching a target usually equals -> Binary Search. We'll use Binary Search here. 
    -> low = 0th index and high = nums.length - 1 and calculate the mid index.
    -> If nums[mid] is the target, we return mid. 
b. Since the array is pivoted: 
    -> One half of the array will be always sorted while the other half, won't be.
    -> We need to find out which half is sorted, because Binary search can only be conducted in a sorted array:
        If nums[low] < nums[mid] -> Means, the left half is sorted.
            Check if target lies within the left half. If not, we need to check the other half.
        Similarly, we do that for the right half as well. 
c. In case we don't find target, we return -1.
*/
class Solution 
{
    public static int search(int[] nums, int target) 
    {
        // If array has only a single element.
        if(nums.length==1)
        {
            if(target==nums[0])
                return 0;
            else 
                return -1;
        } 
        
        int low = 0, high = nums.length-1;
        while(high>=low)
        {
            int mid = (high+low)/2;
            if (target == nums[mid])
                return mid;
            // Check if left half is sorted
            if(nums[low]<=nums[mid])
            {
                if(nums[low]<=target && target<=nums[mid]) // Checking if target belongs in left half.
                    high = mid-1;
                else
                    low = mid+1; // Otherwise we need to check the right half.
            }    
            // Check if right half is sorted
            else
            {
                if(target>=nums[mid] && target<=nums[high]) // Checking if target belongs in right half.
                    low = mid+1;
                else 
                    high = mid-1; // Otherwise need to check in left half.
            }      
        } 
        return -1; // This will be triggered only if in the while loop, we don't find target.
    }
}