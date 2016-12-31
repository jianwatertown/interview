package dynamicProgramming;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such
 * that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with color red;
 costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.


 * Created by jianwang on 12/30/16.
 */
public class PaintHouse {

    public int minCost(int[][] costs) {

        HouseColorCost previousHouse = new HouseColorCost(0,0,0);

        for(int houseIndex=0;houseIndex<costs.length;houseIndex++){
            HouseColorCost newHouse = new HouseColorCost(0,0,0);
            newHouse.r = Math.min(previousHouse.g,previousHouse.b) + costs[houseIndex][0];
            newHouse.g = Math.min(previousHouse.r,previousHouse.b) + costs[houseIndex][1];
            newHouse.b = Math.min(previousHouse.r,previousHouse.g) + costs[houseIndex][2];
            previousHouse = newHouse;
        }
        return previousHouse.getMin();
    }


    public static class HouseColorCost{
        public int r;
        public int g;
        public int b;

        public int getMin(){
            return Math.min(Math.min(r,g),b);
        }

        public HouseColorCost(int r,int g,int b){
            this.r = r;
            this.b = b;
            this.g = g;
        }
    }
}
