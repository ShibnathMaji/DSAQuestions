/*
Intution: 
We aren't using the Pick / Don't Pick method here. 
a. Here, we go through the entire array and pick 1 element at a time, putting it in the List. Mark the picked element in the array as well.
b. Once picked, we forward the marked array and the List of picked elements  to the next recursion call. 
c. In the next recursion call, we iterate over the entire array and only pick the non-marked elements.
d. Base Case is triggered when size of List becomes equal to the array size.
	-> Once triggered, we add the permutation into a List of List.
e. It is mentioned that elements inside the array will be in the range of -10 to 10. 
   We can mark the elemnts picked by replacing the picked element with any number beyond this range.
*/
class Solution 
{
    static void allPermutationsFn(int arr[], List<Integer> permutation, 
                                  List<List<Integer>> allPermutation)
    {
        // Base Case
        if(permutation.size()==arr.length)
        {
            allPermutation.add(new ArrayList<>(permutation));
            return;
        }
        int temp = 0;
        for(int i = 0; i < arr.length; i++)
        {
            temp = arr[i];
            if(temp!=-11) 
            {
                arr[i] = -11;
                permutation.add(temp);
                allPermutationsFn(arr, permutation, allPermutation);
                
                permutation.remove(Integer.valueOf(temp));
                arr[i] = temp;
            }  
        }
    }
    public List<List<Integer>> permute(int[] arr) 
    {
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> allPermutation = new ArrayList<>();
      
        allPermutationsFn(arr, permutation, allPermutation);
        
        return allPermutation;
    }
}
/*
Time Complexity:
There are n! permutations and every time we are iterating through the entire array of n elements.
So, time complexity: n! * n 
Space Complexity: 
For List<Integer> permutation, there can be n elements, 
so space complexity: n
*/