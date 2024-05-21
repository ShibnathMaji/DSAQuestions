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
        
        while(ptr1>=0)
            mergedNums[ptr3--] = nums1[ptr1--];
        
        while(ptr2>=0)
            mergedNums[ptr3--] = nums2[ptr2--];
    
        if(mergedNums.length%2!=0)
            median = mergedNums[(int)mergedNums.length/2];
        else
            median = ((double) mergedNums[(int)mergedNums.length/2 - 1] + (double) mergedNums[(int)mergedNums.length/2]) /2 ;
        
        return median;
    }
}