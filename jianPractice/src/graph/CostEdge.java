package graph;

/**
 * Created by jianwang on 3/27/17.
 */
public class CostEdge {
    int from;
    int to;
    int cost;
    public CostEdge(int a, int b, int cost){
        this.from = a;
        this.to = b;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return ((char)(64+from)) +"---"+(char)(64+to)+" :"+cost;
    }
}
