package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianwang on 1/31/17.
 */
public class Triangle {

    // 2 passes
    public int minimumTotalLeetCode(List<List<Integer>> triangle) {

        if(triangle.size()==1) {return triangle.get(0).get(0);}


        // path sum so far
        int[] result = new int[triangle.size()];

        // result[i] initialized as the last row
        for(int i=0;i<result.length;i++){
            result[i] = triangle.get(triangle.size()-1).get(i);
        }

        // from bottom up
        for(int row=triangle.size()-2;row>=0;row--){

            // each column
            for(int i=0;i<row+1;i++){
                result[i] = Math.min(result[i],result[i+1]) + triangle.get(row).get(i);
            }
        }
        return result[0];
    }


    // one path from top to bottom
    // 4.22.2017 practise
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) {return 0;}
        int[] path = new int[triangle.size()];
        path[0] = triangle.get(0).get(0);
        int result = path[0];

        for(int row=1;row<triangle.size();row++){
            result = Integer.MAX_VALUE;
            for(int i=row;i>=0;i--){
                path[i] = Math.min((i==row)?Integer.MAX_VALUE:path[i],(i==0)?Integer.MAX_VALUE:path[i-1]);
                path[i]+=triangle.get(row).get(i);
                result = Math.min(path[i],result);
            }
        }
        return result;
    }


    public static void main(String[] args){
        Triangle t = new Triangle();

        List<Integer> a = new ArrayList<>();
        a.add(1);

        List<Integer> b = new ArrayList<>();
        b.add(2);
        b.add(3);

        List<List<Integer>> input = new ArrayList<List<Integer>>();
        input.add(a);
        input.add(b);
        System.out.println(t.minimumTotal(input));
    }
}
