/*
Intution:
Here we don't use the Pick/Don't pick method. 
We are given 'n' and 'k', where n = [1,2,3,...,n] and we need to find kth permutation sequence.
If n = 3, i.e, the numbers are [1,2,3] and permutations for this set start with 1, 2, and 3 i.e.,
Starting with 1: "1 23","1 32" -> Permutation number 0, 1
Starting with 2: "2 13","2 31" -> Permutation number 2, 3
Starting with 3: "3 12","3 21" -> Permutation number 4, 5
Using 0-based indexing for numbering the permutations. We also decrease k by 1, due to 0 based indexing.

a. Instead of brute forcing a solution, we need to find the individual numbers in the kth permutation.
b. In the above scenario, excluding the 0th digit, we have 2 more digits i.e., 2! = 2 permutaions per 0th digit.
c. The kth permutation starts with k/2!.
d. Since the permutations adn the digits are unique, once the first element is found, we remove that from the list.
e. Following the above methods we find the remaining elements. 
*/
class Solution 
{
    public String getPermutation(int n, int k) 
    {
        int fact = 1; 
        ArrayList<Integer> numbers = new ArrayList<>(); // Here, we put the elements from 1 to n
        
        for (int i = 1; i < n; i++) 
        {
            fact = fact * i; // Finding out how many permutations per 0th element.
            numbers.add(i);
        }
        numbers.add(n);
        String ans = "";
        k = k - 1; //Since we are using 0 based indexing.
        while (true) 
        {
            ans = ans + "" + numbers.get(k / fact); // -> This gets the index of the digit to be added from the list.
            numbers.remove(k / fact); // -> Unique permutations, so we remove the digit added above.
            if (numbers.size() == 0) // Continuing the while loop till there's one element left
            {
                break;
            }

            k = k % fact; // This gives the next value of k, which is the remainder 
            fact = fact / numbers.size(); // Number of elements are decreasing and so will the number of permutations
        }
        return ans;
    }
}