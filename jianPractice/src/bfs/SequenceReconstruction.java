package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/22/17.
 *
 * Queue based topological sort
 *
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        Map<Integer,Integer> inDegree = new HashMap<>();
        Map<Integer,Set> next = new HashMap<>();
        Queue<Integer> zeroDegree = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        Set<Integer> singlePoint = new HashSet<>();

        // 1. build indegree and edges
        for(List<Integer> edge: seqs){
            for(int i=0;i<edge.size()-1;i++){
                int start = edge.get(i);
                int end = edge.get(i+1);

                if(start)

                Set<Integer> startEdges = next.containsKey(start)? next.get(start): new HashSet<>();
                // only for new edges
                if(!startEdges.contains(end)){

                    startEdges.add(end);
                    next.put(start,startEdges);

                    inDegree.put(end, inDegree.containsKey(end)?inDegree.get(end)+1:1);

                    if(!inDegree.containsKey(start)){
                        inDegree.put(start,0);
                    }
                }
            }
            if(edge.size()==1){
                singlePoint.add(edge.get(0));
            }
        }

        // 2. find 0-degree nodes
        for(int key:inDegree.keySet()){
            if(inDegree.get(key)==0) {zeroDegree.add(key);}
        }

        // 3. more than 1 root or no root - bad
        if(zeroDegree.size()>1 || singlePoint.size()>1) || zeroDegree==1){
            return false;
        }


        // 4. remove 0-outdegree node and all the edges
        while(!zeroDegree.isEmpty()){
            // 5. parent
            int parent = zeroDegree.poll();
            result.add(parent);
            // 6. children
            if(next.containsKey(parent)){
                Set<Integer> children = next.get(parent);
                for(int child:children){
                    inDegree.put(child,inDegree.get(child)-1);
                    if(inDegree.get(child)==0){
                        zeroDegree.add(child);
                    }
                }
            }
            // no way we can have 2 ways to go!
            if(zeroDegree.size()>1){
                return false;
            }
        }

        // 7. validate
        if(result.size()!=org.length){
            return false;
        }
        for(int i=0;i<result.size();i++){
            if(result.get(i)!=org[i]){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args){
        SequenceReconstruction tester = new SequenceReconstruction();
        List<List<Integer>> seqs = new LinkedList<>();

        List<Integer> a = new LinkedList<>(); a.add(1);a.add(2);
        List<Integer> b = new LinkedList<>(); b.add(2);b.add(3);
        List<Integer> c = new LinkedList<>(); c.add(1);c.add(3);

        seqs.add(a);
        seqs.add(b);
        seqs.add(c);

        System.out.println(tester.sequenceReconstruction(
                new int[]{1,2,3},seqs));
    }
}
