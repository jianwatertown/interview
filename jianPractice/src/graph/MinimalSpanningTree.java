package graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


// http://www.cnblogs.com/biyeymyhjob/archive/2012/07/30/2615542.html
public class MinimalSpanningTree {


    // Prim algorithm

    // seen ={u} notSeen ={all vertices}
    // 1. pick a random vertex U from seen,
    // 2. find all edges {u,v} where v belongs to notSeen
    // 3. find smallest edge
    // 4. add v to seen
    // 5. go back to 1
    // 2--3 is


    // Kruskal

    // original graph G1, new graph G2 (no edges)
    // 1. sort all edges O(logE)
    // 2. pick the smallest E,
    //      add to G2 to reduce disconnected components count
    //      (union-find)
    //      edge = {u,v}
    //      if(root(u)!=root(v))
    //      union(u,v)  -   using the small one as root
    //      O(eLogE): E comparison - each compare takes LogE time (the root method)
    //
    // 3. keep doing 2 until count==1


    static Comparator<CostEdge> comp = new Comparator<CostEdge>() {
        public int compare(CostEdge a, CostEdge b) {
            // use cost
            if (a.cost != b.cost)
                return a.cost - b.cost;
            // compare "To"
            if (a.from==b.from) {
                return a.to-b.to;
            }
            else{
                return a.from-b.from;
            }
        }
    };

    public Set<CostEdge> miniTree(List<CostEdge> edges, int size) {

        // 1. sort the edges O(eLogE)
        Collections.sort(edges, comp);

        // 2. init root array
        int[] ids = new int[size+1];     // +1 is for indexes
        for(int i=0;i<=size;i++){
            ids[i] = i;
        }
        Set<CostEdge> tree = new HashSet<>();

        // 3. Kruskal algorithm
        for(int k=0;k<edges.size();k++){
            print(ids);
            System.out.println("Going to add the following edge:");
            System.out.println(edges.get(k));

            int i = edges.get(k).from;
            int j = edges.get(k).to;
            if(root(i,ids)!=root(j,ids)){
                union(i,j,ids);
                tree.add(edges.get(k));
            }
            System.out.println("After edge added");
            print(ids);
            System.out.println();
        }

        return tree;

    }

    // classic root
    int root(int lookup, int[] id){
        // until "your id == yourself"
        // that's the root
        while(lookup!=id[lookup]){
            id[lookup] = id[id[lookup]];
            lookup = id[lookup];
        }
        return lookup;
    }

    // wrong code
    // ids[a] = commonRoot;
    // ids[b] = commonRoot;
    //
    //  Union the roots!!!! not the leaves
    //
    void union(int a, int b, int[] ids){

        int i = root(a,ids);
        int j = root(b,ids);
        System.out.println("i="+(char)(64+i)+" j="+(char)(64+j));
        int commonRoot = i<=j?i:j;
        ids[i] = commonRoot;
        ids[j] = commonRoot;
    }

    public static void main(String[] args){
        List<CostEdge> edges = new LinkedList<>();
        edges.add(new CostEdge(1,4,5));
        edges.add(new CostEdge(3,5,5));
        edges.add(new CostEdge(1,2,7));
        edges.add(new CostEdge(2,5,7));
        edges.add(new CostEdge(5,6,8));
        edges.add(new CostEdge(5,7,9));
        edges.add(new CostEdge(2,4,9));
        edges.add(new CostEdge(6,7,11));
        edges.add(new CostEdge(4,5,15));
        edges.add(new CostEdge(2,3,8));
        edges.add(new CostEdge(4,6,6));

        Set<CostEdge> miniTree = new MinimalSpanningTree().miniTree(edges,7);
        System.out.println("result:");
        int sum = 0;
        for(CostEdge edge:miniTree){
            sum+=edge.cost;
            System.out.println(edge);
        }
        System.out.println("Total cost = "+sum);
    }

    static void print(int[] a){
        for(int b:a){
            System.out.print((char)(64+b)+" ");
        }
        System.out.println();
    }
}