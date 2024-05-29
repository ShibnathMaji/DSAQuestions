class Solution 
{
    public long kthElement( int arr1[], int arr2[], int n, int m, int k) 
    {
        int ptr1 = 0, ptr2 = 0, ptr3=0, ans = 0;
        
        while(ptr1!=n && ptr2!=m && ptr3!=k)
        {
            if(arr1[ptr1]<=arr2[ptr2])
            {
                ans = arr1[ptr1];
                ptr1++; ptr3++;
            }
            else
            {
                ans = arr2[ptr2];
                ptr2++; ptr3++;
            }
            
        }
        while(ptr1!=n && ptr3!=k)
        {
            ans = arr1[ptr1++];
            ptr3++;
        }
        
        while(ptr2!=m && ptr3!=k)
        {
            ans = arr2[ptr2++];
            ptr3++;
        }
        return ans;
    }
}