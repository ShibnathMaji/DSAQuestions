/*
Intution: 
We aren't using the Pick / Don't Pick method here. Instead, we swap the elements of an array to make new permutations.
a. Here, we iterate through the array, picking 1 element at a time. 
b. Once picked, we swap the element with the one present at idx, in the array.
	-> Initially, idx starts with 0, so the first time, the 0th element gets swapped with itself, i.e., 0th elements.
	-> idx gets incremented by 1 and along with the array gets passed to the next recursion call. 
c. In the next recursion call, we start the iteration from idx and similarly swapping of arr[i] and arr[idx] continues.
d. Base Case is triggered when idx becomes equal to the array size.
	-> Once triggered, we add the permutation into a List of List.
	-> Upon returning, the swapped elements are swapped back again into their original positions.
*/
class Solution 
{
    static void swapNumbers(int start, int end, int arr[])
    {
        int temp = 0;
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
    static void allPermutationsFn(int idx, int arr[], List<List<Integer>> allPermutation)
    {
        // Base Case
        if(idx==arr.length)
        {
            List<Integer> tempList = new ArrayList<>();
            for(int i = 0; i<arr.length; i++)
                tempList.add(arr[i]);
            allPermutation.add(new ArrayList<>(tempList));
            return;
        }
        for(int i = idx; i < arr.length; i++)
        {
            swapNumbers(i, idx, arr);
            allPermutationsFn(idx+1, arr, allPermutation);
            swapNumbers(i, idx, arr);
        }
    }
    public List<List<Integer>> permute(int[] arr) 
    {
        List<List<Integer>> allPermutation = new ArrayList<>();
        allPermutationsFn(0, arr, allPermutation);
        return allPermutation;
    }
}