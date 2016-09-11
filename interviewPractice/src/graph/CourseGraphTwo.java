package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
    which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses 
    you should take to finish all courses.
    
    There may be multiple correct orders, you just need to return one of them. If it is impossible to 
    finish all courses, return an empty array.
    
    For example:
    
    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
    So the correct course order is [0,1]
    
    4, [[1,0],[2,0],[3,1],[3,2]]
    There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
    Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
    Another correct ordering is[0,2,1,3].
    
    
    Solution:    This is disjoint-set question variant. 
    1. create a hashmap<root,elements,that need this root>
    2. poplulate the hashmap with all the edges
    3. go through hashmap to find whose size is 1.
    4. bfs the map
    
 * @author jian.wang
 *
 */
public class CourseGraphTwo {
	
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
    	// 1. root:set(root) map
    	int[] result = new int[numCourses];
        Map<Integer, HashSet<Integer>> parentChildren = new HashMap<Integer,HashSet<Integer>>();

        for(int i=0;i<numCourses;i++){
            result[i] = i;                              		// just natural order
        	HashSet<Integer> childSet = new HashSet<Integer>();
        	parentChildren.put(i,childSet);
        }
        
        // 2. outdegree, how many nodes this one depends on
        int[] outdegree = new int[numCourses];
        Arrays.fill(outdegree, 0);
        
        // 2. root:set(children)
        for(int[] edge:prerequisites){
        	int parent = edge[1];
        	int child = edge[0];
        	HashSet<Integer> set = parentChildren.get(parent);
        	set.add(child);											// add child to it
        	outdegree[child]++;										// child outdegree increases
        }
    	
        // 3. find root group and prepare for the BFS
    	Set<Integer> visited = new HashSet<Integer>();
    	Queue q = new LinkedList<>();

        int root = -1;
    	for(int i=0;i<outdegree.length;i++){
    		if(outdegree[i]==0) {
    			q.add(i);
    			visited.add(i);
    		}
    	}
    	
    	// 4. quick exit, can start with no element
    	if(q.size()==0){
    	    return new int[0];
    	}
    	
    	// 5. classic BFS
    	int index = 0;
    	while(!q.isEmpty()){
    		// a. visit parent
    		int parent = (Integer) q.poll();
			result[index++] = parent;
    		
    		// b. enqueue new children
    		for(int child:parentChildren.get(parent)){
    			outdegree[child]--;
        		// not visited and no out-degree
    			if(!visited.contains(child) && outdegree[child]==0){
        			// c. enqueue
        			q.add(child);
            		visited.add(child);
        		}
    		}
    	}
    	return result;
    }
    
    public static void main(String[] args){
    	Character c = 'A';
    	System.out.println(c.toString());
    	
    	int[][] edges = new int[][]{
				  {0,1},
				  {0,2},
				  {1,2},
				};
    	int num = 3;
    	for(int i: new CourseGraphTwo().findOrder(num,edges)){
        	System.out.println(i+" ");
    	}
    }
}
