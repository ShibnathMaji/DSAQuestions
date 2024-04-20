/*
Intution:
It is mentioned that nums[] might have duplicate elements and we need to return unique substrings.
That means no substrings shall be repeating.
a. First we sort the array, to make sure all the repeating numbers are adjacent to each other.
b. While using the "Pick/Don't Pick" method, we need to check for duplicates in nums[]:
	-> Check if the next element is the same as the current element. If yes, we increment idx by 1.
	-> We don't pick an element till we reach the last duplicate number  
		Eg: For nums[] = [4,5,5,5,6], we will continue the loop, till we reach the last 5 i.e., idx = 3 and then pick that element.
	   This prevents picking up duplicate number and also, creating suplicate subsets.
c. Once base case is reached (idx>=nums.length), we crate a deep copy of the 
*/
class Solution
{
    static void allSubsetsRecursion(int[] nums, Set<List<Integer>> allSubsets, 
                                    List<Integer> subset, int idx)
    {
        if(idx>=nums.length)
        {
            allSubsets.add(new ArrayList<>(subset)); // -> creating a deep copy of the subset. T.C: O(N)
            return;
        }
        
        //Picking up element
        subset.add(nums[idx]);
        allSubsetsRecursion(nums, allSubsets, subset, idx+1);
        
        //Not Picking Element
        subset.remove(Integer.valueOf(nums[idx]));
		
		while(idx+1<nums.length && nums[idx] == nums[idx+1) //Make sure idx is not out of bounds and 
			idx++;										    //if the next element is the same, we increment idx till we find the last same element 
		
        allSubsetsRecursion(nums, allSubsets, subset, idx+1);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) 
    {
		Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        Set<List<Integer>> allSubsets = new HashSet<>();
        allSubsetsRecursion(nums, allSubsets, subset, 0);
        
        return allSubsets;
    }
}
/*
Time Complexity:
The "Pick/Don't Pick" recursive method entails we have two choice, Hence, number of recursion calls: 2^N
We add the elements inside the List: N
Hence, Time Complexity: 2^N * N
*/