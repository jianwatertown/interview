package graph;

/**
 * Created by jianwang on 3/27/17.
 */
public class Connection {

    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }
}
