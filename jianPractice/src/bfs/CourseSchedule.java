package bfs;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

	Some courses may have prerequisites, for example to take course 0 you have to first 
	take course 1, which is expressed as a pair: [0,1]

	Given the total number of courses and a list of prerequisite pairs, is it possible 
	for you to finish all courses?

    For example:
    
    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
    
    2, [[1,0],[0,1]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. 
    So it is impossible.
    
    
 * @author jian.wang
 *
 *
 */
public class CourseSchedule {
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// practise March 27, 2017
	// Topological sort
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// BFS
	public boolean canFinish(int numCourses, int[][] prerequisites) {

		int[] outDegree = new int[numCourses];
		Map<Integer,List> toLearn = new HashMap<>();
		Queue<Integer> zeroDegree = new LinkedList<>();

		// 1. set outdegree and edges
		for(int[] pre: prerequisites){
			outDegree[pre[0]] ++;
			toLearn.computeIfAbsent(pre[1],k-> new LinkedList()).add(pre[0]);
		}

		// 2. find 0-degree nodes
		for(int i=0;i<outDegree.length;i++){
			if(outDegree[i]==0) {zeroDegree.add(i);}
		}

		// 3. remove 0-outdegree node and all the edges
		int removed = 0;
		while(!zeroDegree.isEmpty()){
			// 4. parent
			int parent = zeroDegree.poll();
			removed++;
			// 5. children
			if(toLearn.containsKey(parent)){
				List<Integer> children = toLearn.get(parent);
				for(int child:children){
					outDegree[child]--;
					if(outDegree[child]==0){
						zeroDegree.add(child);
					}
				}
			}
		}
		return numCourses==removed;
	}

	public static void main(String[] args){
		CourseSchedule tester = new CourseSchedule();
		int[][] course = new int[1][2];
		course[0] = new int[]{1,0};
		System.out.println(tester.canFinish(2, course));
	}
}








