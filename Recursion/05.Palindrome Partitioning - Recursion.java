/*
Intution:
Here we don't use the Pick/Don't pick method. Instead, we use for-loop to solve this.
We know each individual element is a palindrome.
a. We iterate through the array using the for loop. 
	-> While iterating, we partition the array into substrings and characters chosen for the substring: idx to i 
	-> These substrings are then checked if they are palindrome. 
		If yes, we add them in the List of substrings and proceed with the recursion by incrementing i. 
		If not, we proceed with try finding the next substring which might be palindrome.
		
	-> Once recursion is done, we remove the added substring from the List of substrings.
b. 	Here, the base case is if idx>=s.length.
	Once it is triggered, we add it into allSubstrings.
*/
class Solution 
{
    static boolean isPalindrome(String s, int start, int end)
    {
        while(start<=end)
        {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
    static void palinPartRecursion(String s, int idx, List<String> substring, 
                                   List<List<String>> allSubstrings)
    {
        //Base Case
        if(idx>=s.length())
        {
            allSubstrings.add(new ArrayList<>(substring));
            return;
        }
        
        for(int i = idx; i<s.length(); i++)
        {
            if(isPalindrome(s, idx, i))
            {
                substring.add(s.substring(idx, i+1));
                palinPartRecursion(s, i+1, substring, allSubstrings);
                
                substring.remove(substring.size()-1);
            }  
        }
    }
    public static List<List<String>> partition(String s) 
    {
        List<String> substring = new ArrayList<>();
        List<List<String>> allSubstrings = new ArrayList<>();
        
        palinPartRecursion(s, 0, substring, allSubstrings);
        return allSubstrings;
    }
}