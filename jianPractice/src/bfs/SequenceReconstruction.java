package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/22/17.
 *
 * BFS based topological sort
 *
 */
public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        if(seqs.size()==0&&org.length!=0) {return false;}

        Map<Integer,Integer> indegree = new HashMap<>();
        Map<Integer,Set<Integer>> edgeMap = new HashMap<>();
        List<Integer> result = new LinkedList<>();
        Set<Integer> oneSet = new HashSet<>();

        // 1. dependsCount, edgeMap, from, to init
        for(List<Integer> seq: seqs){
            if(seq.size()<1) {continue;}
            if(seq.size()==1){oneSet.add(seq.get(0));continue;}
            for(int i=0;i<seq.size()-1;i++){
                int fromCourse = seq.get(i);
                int toCourse = seq.get(i+1);

                // increase indegree only when first time seeing the edge
                if(!edgeMap.containsKey(fromCourse)|| !edgeMap.get(fromCourse).contains(toCourse)){
                    indegree.put(toCourse,indegree.containsKey(toCourse)?indegree.get(toCourse)+1:1);
                }

                // add to edge map
                if(!edgeMap.containsKey(fromCourse)) {
                    edgeMap.put(fromCourse,new HashSet<>());
                }
                edgeMap.get(fromCourse).add(toCourse);
            }
        }

        // 2. BFS
        Queue<Integer> noInDegreeQ = new LinkedList<>();
        for(Integer fromCandidate: edgeMap.keySet()){
            if(!indegree.containsKey(fromCandidate)) {noInDegreeQ.add(fromCandidate);}
        }

        for(Integer one: oneSet){
            if(!indegree.containsKey(one)&&!edgeMap.keySet().contains(one)) {noInDegreeQ.add(one);}
        }

        while (!noInDegreeQ.isEmpty()){
            if(noInDegreeQ.size()>1) {return false;}            // <----- key to the algorithm
            Integer course = noInDegreeQ.poll();
            result.add(course);
            if(edgeMap.containsKey(course)){
                for(Integer child: edgeMap.get(course)){
                    indegree.put(child,indegree.get(child)-1);
                    if(indegree.get(child)==0){
                        noInDegreeQ.add(child);
                    }
                }
            }
        }

        if(result.size()!=org.length) {return false;}
        for(int i=0;i<result.size();i++){
            if(result.get(i)!=org[i]) {return false;}
        }
        return true;
    }
}
