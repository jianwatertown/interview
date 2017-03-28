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
 * Wrong: https://en.wikipedia.org/wiki/Disjoint-set_data_structure !!!!!
 * Correct: this problme use Topological-sort to solve
 * https://www.youtube.com/watch?v=n_yl2a6n7nM
 *
 *
 */
public class CourseSchedule {
	
	/**
	 * This is a disjoint-set problem, steps 
	 * 
	 * 	
	 * 1. make set for each element
	 * 2. for every edge, call "union"
	 * 2. count how many root node there is 
	 * 
	 * Time: Edge * LogN
	 * 
	 * Disjoint-Set revisit: with root compression, but not *weighted* as who is root is determined
	 * 
	 *  0. keep a size
	 *  
	 * 	1. find{call root()}
	 *  2. root{recursively call root()}
	 *  3. union{child root-> parent root}
	 * 
	 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //0. edge case
        if(prerequisites==null || prerequisites.length<1){return true;}

    	// 1. construct forest
    	int id[] = new int[numCourses];    	
    	for(int i=0;i<numCourses;i++){
    		id[i] = i;
    	}    	
    	
    	// 2. go through the edges
    	for(int[] relation:prerequisites){
    		// 3. if cannot make a union, quite the loop
    		if(!union(id,relation[0],relation[1])){
    			return false;
    		}
    	}
    	
    	// 4. ok!
    	return true;
    }
    
    
    // classic root  with path compression
    public int root(int lookup,int[] id){
    	// until "your id == yourself"
    	while(id[lookup]!=lookup){
    		id[lookup] = id[id[lookup]];
    		lookup=id[lookup];
    	}
    	return lookup;
    }
    
    // union is the key here, it needs to find out circular relationships
    public boolean union(int[] ids, int child, int parent){

    	int i = root(child,ids);
    	int j = root(parent,ids);

    	if(i==j) {return false;}
		ids[j] = i;
		return true;
    }


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// practise Feb 6, 2017
	// Topological sort
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// BFS
	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {

		int[] outDegree = new int[numCourses];
		Map<Integer,List> parents = new HashMap<>();
		Queue<Integer> zeroDegree = new LinkedList<>();

		// 1. set outdegree and edges
		for(int[] pre: prerequisites){
			outDegree[pre[0]] ++;
			List<Integer> edges = parents.containsKey(pre[1])? parents.get(pre[1]) : new LinkedList<>();
			edges.add(pre[0]);
			parents.put(pre[1],edges);
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
			if(parents.containsKey(parent)){
				List<Integer> children = parents.get(parent);
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
		System.out.println(tester.canFinishBFS(2, course));
	}
}








