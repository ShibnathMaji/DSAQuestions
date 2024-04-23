/*
Intution:
We combine Subsets 2 and Combination Sum 1 codes for this one.
The array given might have duplicate elements and we need to generate unique subsets that sum up to the given target.
We use the "Pick/Don't Pick" method to choose elements and iterate through the array.   
a. Since an element can be picked only ONCE:
	-> Upon picking, we increment the index by 1 and reduce the target amount by the picked value
	-> Index gets incremented regardless of the element being picked or not.
	-> Once pickup loop ends, we remove the picked element
b. For making sure that duplicates aren't picked: 
	-> We sort the input array. This allows duplicate elements to be adjacent to one another. 
	-> If we encounter any duplicates, we increment idx till we reach the last duplicate element.
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
    static void combSum2Recursion(int idx, int target, int candidates[],
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
        
        //Pick up element
        currCombination.add(candidates[idx]);
        combSum2Recursion(idx+1, target-candidates[idx], candidates, 
                          currCombination, allCombinations);
        
        //Don't Pick up element
        currCombination.remove( Integer.valueOf(candidates[idx]) );
        while((idx+1)<candidates.length && candidates[idx]==candidates[idx+1]) //-> Skipping duplicate elements
            idx++;
        combSum2Recursion(idx+1, target, candidates, currCombination, allCombinations);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) 
    {
        Arrays.sort(candidates);
        List<Integer> currCombination = new ArrayList<>();
        List<List<Integer>> allCombinations = new ArrayList<>();
        combSum2Recursion( 0, target, candidates, currCombination, allCombinations);
        
        return allCombinations;
    }
}

/*
Time Complexity: 2^N
*/