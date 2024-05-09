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
        //Check if the non-duplicate element is at the begining or at the end
        if(nums[0]!=nums[1])
            return nums[0];
        if(nums[nums.length-1]!=nums[nums.length-1])
            return nums[nums.length-1];

        int low = 0, high = nums.length-1, mid = 0;
        while(high>=low)
        {
            mid = (high+low)/2;
            System.out.println("Low: "+ low + " high: " + high + " mid: "+ mid);
            
            String evenOrOdd = (mid%2==0)?"E":"O";
            System.out.println("Mid is : "+ evenOrOdd);
            
            if(mid+1 == nums.length)
                return nums[nums.length-1];
            if(mid-1<0)
                return nums[0];

            int midLeft = nums[mid - 1];
            int midRight = nums[mid + 1];
            System.out.println("midLeft: "+ midLeft + " midRight: " + midRight);

            if(mid!=midLeft && mid!=midRight && midLeft!=midRight)
                return nums[mid];

            if( (evenOrOdd == "E" && nums[mid+1]==nums[mid])
            || (evenOrOdd == "O" && nums[mid-1]==nums[mid]))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return nums[mid];
    }
}