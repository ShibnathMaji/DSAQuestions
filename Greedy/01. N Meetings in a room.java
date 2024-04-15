/*
Intution: 
a. We need to maximize the number of meetings in one room.
b. For that, we need to prioritize the meetings whose end times are smaller.
    -> End Time as in the value in end[], not the meeting duration
    -> Smaller end values means, the room is free more often for next meeting 
    i.e., we can conduct more meetings.
    -> Sort the meetings in ascending order of end time.
    -> DON'T focus on meeting duration. Focus on End time.
c. Once sorted, we need to iterate through the sorted times, 
   and check if start[i] > end[i-1]:
    -> If yes, increase counter.
*/
class Solution 
{
    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    public static int maxMeetings(int start[], int end[], int n)
    {
        List<List<Integer>> listOfMeetings = new ArrayList<>();
        
        for(int i = 0 ; i < n ; i++)
            listOfMeetings.add(List.of(start[i], end[i], i+1));
            
        Collections.sort(listOfMeetings, new customComparator());
        int prevMeetingEnd = listOfMeetings.get(0).get(1);
        
        int counter = 1; //Since the first meeting will happen
        for(int i = 1; i<n; i++)
        {
            int currMeetStart = listOfMeetings.get(i).get(0);
            if(currMeetStart > prevMeetingEnd)
            {
                prevMeetingEnd = listOfMeetings.get(i).get(1);
                counter++;
            }
                
        }
        return counter;
    }
}
class customComparator implements Comparator<List<Integer>>
{
    @Override
    public int compare(List<Integer> L1, List<Integer> L2)
    {
        if(L1.get(1) > L2.get(1))
            return 1;
        else if(L1.get(1) < L2.get(1))
            return -1;
        else if(L1.get(2) > L2.get(2))
            return 1;
        else
            return -1;
    }
}