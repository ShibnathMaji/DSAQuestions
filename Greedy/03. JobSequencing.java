/*
Intution:
a. Since we need to maximize profit, we sort arr[] in descending order based on the profit.
b. Then based on the deadline, we will postpone the task given, to the very last day.
    -> This way, we will have more number of free days ahead to complete other tasks 
       which has lesser deadlines.
c. We need to crate an array which will store the job ids. 
    -> task_arr[] of size n and initialise it with value -1.
    -> This is to signify the cell is empty.
d. After this, we iterate over the job arr[].
    -> For eg: We will put a job with the deadline 2 on index (2-1) i.e., 1 in task_arr[]. 
    -> If index 1 isn't having -1 value, we iterate backwards till index 0 
       searching for an index with -1 value. 
       Once we get it, we put the update the value of that index with the job id.
       Then increment the job counter and the add up the profit for that job. 
    -> If there are no cells with -1 value, we leave that particular job.
*/
class customComparator implements Comparator<Job>
{
    @Override
    public int compare(Job J1, Job J2)
    {
        return J2.profit - J1.profit;
    }
}
class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        // Sorting the array based on profit in descending order.
        Arrays.sort(arr, new customComparator()); // -> NLog N
        
        //Filling task_arr
        int task_arr[] = new int[n];
        Arrays.fill(task_arr, -1);
        
        int maxProfit = 0;
        int taskCounter = 0;
        
        for(int i=0; i<n; i++)
        {
            //"deadLine" is serving as the index for task_arr
            int deadLine = arr[i].deadline-1;

            while(deadLine>=0) // -> Check whether the index is 0 or more.
            {
                if(task_arr[deadLine]==-1) // -> Check whether the value in that cell is -1. 
                {
                    task_arr[deadLine] = arr[i].id;
                    taskCounter++;
                    maxProfit+=arr[i].profit;
                    
                    break; // -> Since we are able to put the job id, we break out of the while loop
                }
                deadLine--; // -> This gets executed if the value in that index isn't -1. 
                            //    We check the cell before that one.
            }
        }
        int answer[] = {taskCounter, maxProfit};
        return answer;
        
    }
}

/*
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
*/