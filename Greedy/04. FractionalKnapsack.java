/*
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
*/
/*
Intution:
For this, we need to prioritise items whose value/weight ratio is maximum.
So, we sort the Item arr[] on the basis of value/weight ratio in descending order.

a. Once sorting is done, we iterate over the array, taking the higher value/weight items first.
b. We take whole value of the items as long as the whole value weight doesn't exceed capacity and 
   proceed through the array.
c. If we can't take any more whole value of an item, but we still have capacity to take more:
    -> we calcuate how much fraction is remaining
    -> Once done, we calcute what's the value of the remaining fraction and add it to the value.
*/
class customComparator implements Comparator<Item>
{
    @Override
    public int compare(Item i1, Item i2)
    {
        double value1 = (double) i1.value / (double) i1.weight;
        double value2 = (double) i2.value / (double) i2.weight;
        
        if(value1 < value2)
            return 1;
        else if(value1 == value2)
            return 0;
        else 
            return -1;
    }
}
class Solution
{
    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Sorting the array in terms of value/weight ratio in descending order.
        Arrays.sort(arr, new customComparator()); // -> NLog N
        
        double currCapacity = 0.0 , currValue = 0.0;
        for(int i = 0; i < n; i++)
        {
            if(currCapacity + arr[i].weight < W) // Calculates the whole number portion of capacity
            {
                currCapacity+=arr[i].weight;
                currValue+=arr[i].value;
            }
            else // Calculates the remaning fractional part of the capcaity
            {
                double capacityDifference = (double)W - currCapacity;
                currValue+=( ( (double)arr[i].value / (double)arr[i].weight ) * capacityDifference );

                break; //Since we are already taking the remaining fractional part of the Item,
                       //there's no need to continue this loop anymore.
            }    
        }
        return currValue;
    }
}