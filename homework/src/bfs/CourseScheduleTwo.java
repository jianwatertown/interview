package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/11/17.
 */
public class CourseScheduleTwo {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] result = new int[numCourses];
        int counter = -1;
        Map<Integer,Integer> dependsCount = new HashMap<>();
        Map<Integer,Set<Integer>> edgeMap = new HashMap<>();
        Set<Integer> from = new HashSet<>();

        for(int i=0;i<numCourses;i++){from.add(i);}

        // 1. dependsCount, edgeMap, from, to initi
        for(int[] edge: prerequisites){
            int fromCourse = edge[1];
            int toCourse = edge[0];
            // edge map
            if(!edgeMap.containsKey(fromCourse)) {edgeMap.put(fromCourse,new HashSet<>());}
            edgeMap.get(fromCourse).add(toCourse);
            // from/to set
            if(from.contains(toCourse)) {from.remove(toCourse);}
            // depednecy count
            if(!dependsCount.containsKey(toCourse)) {dependsCount.put(toCourse,0);}
            dependsCount.put(toCourse,dependsCount.get(toCourse)+1);
        }

        // 2. BFS
        Queue<Integer> noInDegreeQ = new LinkedList<>();
        noInDegreeQ.addAll(from);

        while (!noInDegreeQ.isEmpty()){
            Integer course = noInDegreeQ.poll();
            result[++counter]=course;
            if(edgeMap.containsKey(course)){
                for(Integer child: edgeMap.get(course)){
                    dependsCount.put(child,dependsCount.get(child)-1);
                    if(dependsCount.get(child)==0){noInDegreeQ.add(child);}
                }
            }
        }
        return counter==numCourses-1?result:new int [0];
    }
}
