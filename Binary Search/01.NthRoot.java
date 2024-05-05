/*
Return 0 -> If ans < m  => Search Range needs to be = Right half ( mid+1 to high )
Return 1 -> If ans = m 
Return 2 -> If ans > m  => Search Range needs to be = Left Half ( low to mid-1 )
    While multiplying "ans" n times, we might exceed the limit of int or long, creating an overflow error. 
    So, we check if the current value of ans has exceeded m or not. 
    If at any point it exceeds m, we break out of the loop, knowing "mid" is higher than the the desired
    number and reduce our search range to the left half.
*/ 
public class Solution 
{
	static int midTimesN(int n, int m, int mid)
	{
		//Here we compare the value of mid^n with m and return value accordingly
		long ans = 1;  // long insteead of int since multiplying the value might cross int's range
		
		for(int i = 1; i<=m; i++)
		{
			ans*=mid;
			if(ans>m) // Since we surpass the value given to us (m), there's no point priceeding further
				return 2; // We return knowing "mid" and everything to the right will exceed given value (m).
		}
		if(ans==m)
			return 1; // The nth root of m is found.
		
		return 0; // Case is triggered when the value is too small and the right half needs to be considered as search space.
	}
	
	public static int NthRoot(int n, int m) 
    {
		// Intially range = 1 to m
		int low = 1, high = m;
		
		
		while(low<=high)
		{
			int mid = (low+high)/2;
			
			int midPowN = midTimesN(n,m,mid);
			
			if(midPowN == 0)
				low = mid + 1;
			else if(midPowN == 2)
				high = mid - 1;
			else if(midPowN == 1)
				return mid;
		}
		return -1; //This is triggered when there is no nth root for mid
	}		
}
