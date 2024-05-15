class Solution 
{
    public static int search(int[] nums, int target) 
    {
        if(nums.length==1)
        {
            if(target==nums[0])
                return 0;
            else 
                return -1;
        } 
        
        int low = 0, high = nums.length-1;
        int mid = 0;
        while(high>=low)
        {
            mid = (high+low)/2;
            if (target == nums[mid])
                return mid;
            // Check if left half is sorted
            if(nums[low]<=nums[mid])
            {
                if(nums[low]<=target && target<=nums[mid])
                    high = mid-1;
                else
                    low = mid+1;
            }    
            // Check if right half is sorted
            else
            {
                if(target>=nums[mid] && target<=nums[high])
                    low = mid+1;
                else 
                    high = mid-1;
            }      
        } 
        return -1;
    }
}