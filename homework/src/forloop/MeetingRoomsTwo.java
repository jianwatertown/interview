package forloop;

import java.util.*;

public class MeetingRoomsTwo {
    public int minMeetingRooms(Interval[] intervals) {

        if(intervals==null||intervals.length==0) {return 0;}

        // 1. sort the meeting time by starting time
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });

        // 2. meeting room heap sorted by end time
        PriorityQueue<Interval> meetingRoomEnd = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end-o2.end;
            }
        });

        // 3. add meetings
        meetingRoomEnd.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            Interval lastMeeting = meetingRoomEnd.poll();

            // 4. check if this overlaps with new one
            // 4.1 no overlap
            if(intervals[i].start>=lastMeeting.end) {
                lastMeeting.end = intervals[i].end;
            }
            // 4.2 overlaps
            else{
                meetingRoomEnd.offer(intervals[i]);
            }
            meetingRoomEnd.offer(lastMeeting);
        }

        return meetingRoomEnd.size();
    }
}
