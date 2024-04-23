/*
Intution:
We can pick an element unlimited times and we are to return unique combinations.
For that, we use the "Pick/Don't Pick" method and iterate through the array.
a. When we are Picking an element, we might need to pick it up more than once to
    reach the target. 
    -> Hence we DONT increment the index. 
    -> The element being picked must NOT be greater than target.
    -> The new target is the element subtracted from the old target
    -> Once the "pick up" loop returns, we need to remove the picked element
b. We won't be picking up any element here
    -> We just incremement the index.
    -> This increment helps iterate through the array.
c. Two base cases this time: 
	-> Base Case 1: 
		If the target sum is reached, we add the deepcopy of currCombination in the allCombinations.
		This case might get triggered even if the the array is NOT completely traversed.
	-> Base Case 2:
		If the target is less than 0 : 
			It means the numbers in the currCombination add up to a value greater than 0. 
			So, we end the loop, backtrack and remove numbers from the currCombination.
		If idx is out of bounds:
			It means we have traversed the array but the elements in currCombination don't sum up to the target.
			So we return.
*/
class Solution 
{
    static void combSumRecursion(int idx, int target, int candidates[],
        List<Integer> currCombination, List<List<Integer>> allCombinations)
    {
        // Base case 1
        if(target==0)
        {
            allCombinations.add(new ArrayList<>(currCombination));    
            return;
        }
        // Base Case 2
        if(target < 0 || idx>=candidates.length)
            return;
        
        //Pick up Element
        if(candidates[idx]<=target)
        {
            currCombination.add(candidates[idx]);
            combSumRecursion(idx, target-candidates[idx], candidates, 
                             currCombination, allCombinations);
            
            currCombination.remove(Integer.valueOf(candidates[idx]));
        }
        
        //Don't Pick Element
        combSumRecursion(idx+1, target, candidates, currCombination, allCombinations);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        List<Integer> currCombination = new ArrayList<>();
        List<List<Integer>> allCombinations = new ArrayList<>();
        combSumRecursion( 0, target, candidates, currCombination, allCombinations);
        
        return allCombinations;
    }
}
/*
Time Complexity:
We are given a target T. And for each element in the array, we can choose to pick it or not. 
So 2 choices, per candidate, till we reach target T: O(2^T)
Let's say the ArrayList we are putting in allCombinations is of average length K.
So, time taken per recursion call: O(K)
Hence final time complexity: 2^T * K
*/