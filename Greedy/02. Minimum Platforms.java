/*
Intuition:
Here, we basically need to find the maximum number of time overlaps.
That is the minimum number of platforms required.
View the arrival and departure times in ascending order.

If one platform is free, it is available for the next train.

a. We only need to care about the arrival time and the departure time,
   not about the ordering between them.
b. Sort both the arr[] and dep[] in ascending order, independently.  
c. Use two pointers for the two arrays i.e., arrPtr for arr[] and depPtr for dep[]
   Set both of them to 0.
d. We will iterate as long as arrPtr < n (i.e., array size)
    If the next arrival time ( arr[arrPtr] ) < previous train's departure ( dep[depPtr] ),
        Increase the platform count by 1.
    If not, we decrease the platform count by 1.
e. Need to store the max value of the counter and print it. 
*/
class Solution
{
    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    static int findPlatform(int arr[], int dep[], int n)
    {
        // If no train arrives, no platform needed
        if(n==0)
            return 0;
        // If only 1 train arrives, 1 platform is needed
        if(n==1)
            return 1;
            
        // Sorting both the arrays independently.
        Arrays.sort(arr); // -> NlogN
        Arrays.sort(dep); // -> NlogN
        
        int numOfPlatforms = 0, counter = 0;
        int arrPtr = 0, depPtr = 0;
        
        while(arrPtr < n)
        {
            if(arr[arrPtr] <= dep[depPtr]) // -> This means, a timing overlap and we need an extra platform.
            {
                counter++;
                arrPtr++;
                numOfPlatforms = Math.max(numOfPlatforms, counter);
                
            }
            else if(arr[arrPtr] > dep[depPtr]) // -> This means, platform is available and we are reducing the counter
            {
                counter--;
                depPtr++;
            }
            
        }
        return numOfPlatforms;
    }
}