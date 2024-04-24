/*
An alternate method of solving this by using For Loop and recursion
a. For avoiding duplicates, we sort the array. The duplicates will be adjacent to each other.
b. While iterating through the array, we will check the previous element and current element
    -> If found same, we skip it.
    -> Next, we add the element in the currCombination
    -> Since this eleemnt is complete, we increment the idx value and decrease target
        by candidate[i]. 
    -> After this, we call the recusrion. Once finished, we remove the element added.
c. Two base cases:
    -> If target is 0, that means elements in the currCombination gets deep-copied to 
        allCombinations
    -> If target is negative (i.e., less than 0), we return since there's no point
        continuing the particular combination. 
*/
class Solution 
{
    static void combSum2Recursion(int idx, int target, int candidates[],
        List<Integer> currCombination, List<List<Integer>> allCombinations)
    {
        //Base Case 1
        if(target==0)
        {
            allCombinations.add(new ArrayList<>(currCombination));
            return;
        }
        //Base Case 2
        if(target < 0)
            return;

        int previous = -1; //Initialising previous to a number that won't be in the array.
        for(int i = idx; i<candidates.length; i++) // Iterating through the array
        {
            if(candidates[i]==previous) //Skipping duplicates
               continue;
            
            // Add Element
            currCombination.add(candidates[i]);
            combSum2Recursion( i+1, target-candidates[i], candidates, currCombination,
                              allCombinations);
            // Remove Element
            currCombination.remove(Integer.valueOf(candidates[i]));
            
            previous = candidates[i];
        }
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