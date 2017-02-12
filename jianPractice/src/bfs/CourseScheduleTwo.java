package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/11/17.
 */
public class CourseScheduleTwo {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // edges and indgrees
        Map<Integer, List> next = new LinkedHashMap<>();
        int[] indgree = new int[numCourses];

        // result
        List<Integer> order = new LinkedList<>();
        int[] result = new  int[numCourses];

        // 1. build the graph edage and indgrees
        buildEdgeAndInDegrees(indgree,next,prerequisites);

        // 2. getting all the indgree 0 element
        Queue<Integer> zeroIndgreeQ = new LinkedList<>();
        for(int i=0;i<indgree.length;i++){
            if(indgree[i]==0){
                zeroIndgreeQ.add(i);
            }
        }
        System.out.println(zeroIndgreeQ.size());
        // 3. keep adding 0-indegree element to the result
        while(!zeroIndgreeQ.isEmpty()){
            Integer parent = zeroIndgreeQ.poll();
            order.add(parent);
            // 4. visit each chidlren
            if(next.containsKey(parent)){
                List<Integer> childrenList = next.get(parent);
                for(Integer child: childrenList){
                    // 5. reduce each child's indgree
                    indgree[child]--;
                    if(indgree[child]==0){
                        zeroIndgreeQ.add(child);
                    }
                }
            }
        }

        if(order.size()==numCourses){
            for(int i=0;i<order.size();i++){
                result[i] = order.get(i);
            }
            return result;
        }
        else{
            return new int[]{};
        }
    }

    public void buildEdgeAndInDegrees(int[] indgree, Map<Integer,List> children, int[][] prerequisites){
        for(int i=0;i<prerequisites.length;i++){
            int parent = prerequisites[i][1];
            int child = prerequisites[i][0];
            indgree[child]++;
            if(!children.containsKey(parent)){
                children.put(parent,new LinkedList<Integer>());
            }
            children.get(parent).add(child);
        }
    }

    public static void main(String[] args){
        CourseScheduleTwo tester = new CourseScheduleTwo();
        int[] result = tester.findOrder(2,new int[][] { {1,0}, {0,1}});
        for(int i:result)
        System.out.println(i);
    }
}
