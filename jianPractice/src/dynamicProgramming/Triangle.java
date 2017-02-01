package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianwang on 1/31/17.
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle.size()==1) {return triangle.get(0).get(0);}


        // path sum so far
        int[] result = new int[triangle.size()];
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
