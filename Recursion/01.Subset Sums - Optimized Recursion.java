/*
Intution:
We run a recursive loop and iterate through the given array using two options:
a. We pick the element in the array
    -> In this case, we pick it up and add it to our sum. 
b. We choose not to pick the element in the array
    -> Since we didn't pick the element, there would be no summing it up either
For iterating through the array we use the int i. It gets iterarted regardless 
of an element getting picked or not.
Once i reaches the arr.size(), we put the sum of the subsets in the ArrayList sumArr
and break out of the recursive loop.
*/

class Solution 
{
    static void subSetSumRecursion(ArrayList<Integer> arr, ArrayList<Integer> sumArr, int i, int sum)
    {
        if(i==arr.size()) // -> Break the recursive loop once i reaches array length. 
        {
            sumArr.add(sum); // -> Puts the sum of the current sebset elements in sumArr.
            return;
        }
        
        //Not Picking elements
        subSetSumRecursion(arr, sumArr, i+1, sum); //i+1 -> Since the iteration continues regardless of 
                                                   // of the element getting picked. 
        
        //Picking elements
        subSetSumRecursion(arr, sumArr, i+1, sum+arr.get(i)); // Add the picked element to int sum.
    }
    static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) 
    {
        ArrayList<Integer> sumArr = new ArrayList<Integer>(); // This will store each subset's sum
        subSetSumRecursion(arr, sumArr, 0, 0);

        return sumArr;
    }
}