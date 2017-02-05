package bfs;

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
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
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
    	while(id[lookup]!=lookup){			// a.id -> b
    		id[lookup] = id[id[lookup]];	// a.id -> b.id
    		lookup=id[lookup];				// now look up for b
    	}
    	return lookup;
    }
    
    // union is the key here, it needs to find out circular relationships
    public boolean union(int[] id, int child, int parent){
    	
    	int grandParent = root(parent,id);
    	if(grandParent==child){
    		return false;
    	}
    	else{
    		id[child] = grandParent;
    		return true;
    	}
    }
}







