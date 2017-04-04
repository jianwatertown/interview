package graph;

/**
 * Created by jianwang on 4/4/17.
 */
public class Edge {
    public GraphNode from;
    public GraphNode to;
    public int distance;
    public Edge(GraphNode a, GraphNode b, int distance){
        from = a;
        to = b;
        distance = distance;
    }
}
