/*
Intution : Using two pointers method to find the medians.
a. This involves creating another array of length nums1.length + nums2.length. 
   It'll store the eleemnts of both nums1 and nums 2 in sorted manner.
   We use two pointers to compare the values of nums1 and nums2 and add the value into the new array accordingly.
b. Once we get the array, we can just find the median/medians and return that.
*/
class Solution 
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        int mergedNums[] = new int [nums1.length + nums2.length];
        double median = 0.0;
        
        int ptr1 = nums1.length-1, ptr2 = nums2.length-1;
        int ptr3 = mergedNums.length-1;
        
        while(ptr1>=0 && ptr2>=0)
            mergedNums[ptr3--] = (nums2[ptr2] > nums1[ptr1]) ? nums2[ptr2--] : nums1[ptr1--];
        
		// Only one of these two will run, since the other one would've been traversed completely
        while(ptr1>=0)
            mergedNums[ptr3--] = nums1[ptr1--];
        
        while(ptr2>=0)
            mergedNums[ptr3--] = nums2[ptr2--];
		
		//Now that we have got the entire array - sorted, it is easy to find out the median.
        if(mergedNums.length%2!=0)
            median = mergedNums[(int)mergedNums.length/2];
        else
            median = ((double) mergedNums[(int)mergedNums.length/2 - 1] + (double) mergedNums[(int)mergedNums.length/2]) /2 ;
        
        return median;
    }
}