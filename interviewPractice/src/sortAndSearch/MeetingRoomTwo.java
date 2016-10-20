package sortAndSearch;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * 	find the minimum number of conference rooms required.
 *  
    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return 2.
    
 * @author jian.wang
 *
 */
public class MeetingRoomTwo {
    public int minMeetingRooms(Interval[] intervals) {
    	int[] starts = new int[intervals.length];
    	int[] ends = new int[intervals.length];

    	// get all starts and end
        for(int i=0;i<intervals.length;i++){
        	starts[i] = intervals[i].start;
        	ends[i] = intervals[i].end;
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int max_concurrent_meetings = 0;
        int meetings = 0;
        int endIndex = 0;
        for(int startIndex=0; startIndex<starts.length;startIndex++){
        	while(endIndex<starts.length&& 
        			ends[endIndex]<=starts[startIndex])	// meetings have finished
        	{	
        		endIndex++;
        	}
        	meetings = startIndex-endIndex+1;
        	max_concurrent_meetings = Math.max(max_concurrent_meetings, meetings);
        }
        return max_concurrent_meetings;
    }
	
    public int minMeetingRoomsSlow(Interval[] intervals) {
        List<Integer> starts = new LinkedList<Integer>();
        List<Integer> ends = new LinkedList<Integer>();
 
        // get all starts and end
        for(Interval inl:intervals){
        	starts.add(inl.start);
        	ends.add(inl.end);
        }
        
        Collections.sort(starts);
        Collections.sort(ends);
        int max_concurrent_meetings = 0;
        int meetings = 0;
        int endIndex = 0;
        for(int startIndex=0; startIndex<starts.size();startIndex++){
        	while(endIndex<starts.size()&& 
        			ends.get(endIndex)<=starts.get(startIndex))	// meetings have finished
        	{	
        		endIndex++;
        	}
        	meetings = endIndex-startIndex;
        	max_concurrent_meetings = Math.max(max_concurrent_meetings, meetings);
        }
        return max_concurrent_meetings;
    }

    public class Interval {
    	 int start;
    	 int end;
    	 Interval() { start = 0; end = 0; }
    	 Interval(int s, int e) { start = s; end = e; }
    }
}


