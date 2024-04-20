/*
Intution:
It is mentioned that nums[] might have duplicate elements and we need to return unique substrings.
That means no substrings shall be repeating.
For this "no repeating" criteria, we use Set data structure. In Set, we don't have duplicate elements.
a. We initialize a List of integers which will be the subsets. 
   Also, we initialise a Set of List of integers, which will contain all of the subsets formed.
b. Now, we use the "Pick / Dont Pick" method to Recursively choose elements for creating the subset.
	-> Increment index by 1 regardless of the element being chosen or not.
	-> When "Not Picking" the element, we remove that particular element we added (subset.remove(Integer.valueOf(nums[idx]))),
	   rather the last element of the subset (subset.remove(subset.size()-1)). 
c. The Base case of the recursion is triggered once idx >= nums.length
	-> Before adding the subset, we sort the subset. 
		Since the nums[] may contain repeating elements, so the "Pick / Don't Pick" might make subsets like (2,1,3), (3,1,2) and (1,2,3). 
		These 3 subsets are the same, but Set data structure will not consider that, until all of the elements in there are ordered the same.
		Hence we sort the subsets.
	-> Once sorted, we add the subset to the Set. Set handles removing duplicates.
		While adding, we need to create a new ArrayList which has the values of the subset. 
		It'll copy the values of the subset into a list an add that list in the Set.
		allSubsets.add(subset) -> only creates a reference of the subset.
		allSubsets.add(new ArrayList<>(subset)) -> This is the correct approach.
d. Since the return type needs to be List<List<Integer>>, we copy the Lists present in Set and add it in a List of Lists. 		
*/
class Solution
{
    static void allSubsetsRecursion(int[] nums, Set<List<Integer>> allSubsets, 
                                    List<Integer> subset, int idx)
    {
        if(idx>=nums.length)
        {
			Collections.sort(subset); // -> NlogN
            allSubsets.add(new ArrayList<>(subset)); // // -> creating a deep copy of the subset. T.C: O(N)
            return;
        }
        
        //Picking up element
        subset.add(nums[idx]);
        allSubsetsRecursion(nums, allSubsets, subset, idx+1);
        
        //Not Picking Element
        subset.remove(Integer.valueOf(nums[idx]));
        allSubsetsRecursion(nums, allSubsets, subset, idx+1);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) 
    {
        List<Integer> subset = new ArrayList<>();
        Set<List<Integer>> allSubsets = new HashSet<>();
        allSubsetsRecursion(nums, allSubsets, subset, 0);
        
        List<List<Integer>> answer = new ArrayList<>(); 
        for(List L1:allSubsets) // -> N
            answer.add(L1);
        
        return answer;
    }
}
/*
Time Complexity:
The "Pick/Don't Pick" recursive method entails we have two choice, Hence, number of recursion calls: 2^N
We sort the elements each time a loop is completed: NlogN
Hence, Time Complexity: 2^N * NlogN
*/