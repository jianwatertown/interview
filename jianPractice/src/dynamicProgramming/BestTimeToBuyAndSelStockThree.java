package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 4/18/17.
 *
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete at most two transactions.


 */
public class BestTimeToBuyAndSelStockThree {

    // try to sell at different i
    // f[i]  = max(f[i-1] + price[i] - price[j]) for all j (0,i-1)
    public int maxProfit(int[] prices) {

        if(prices==null||prices.length<2) {return 0;}
        int len = prices.length;
        int[] profit = new int[len];
        Arrays.fill(profit,-1);
        profit[0] = 0;
        return maxProfit(prices,len-1,profit);
    }

    public int maxProfit(int[] prices, int i, int[] profit){
        if(i<0) {return 0;}
        if(profit[i]!=-1)   {return profit[i];}
        int max = 0;
        for(int j=0;j<i;j++){
            int p2 = prices[i]>prices[j]?prices[i]-prices[j]:0;
            max = Math.max(maxProfit(prices,j-1,profit)+ p2,max);
        }
        profit[i] = max;
        return max;
    }

    public static void main(String[] args){
        BestTimeToBuyAndSelStockThree test = new BestTimeToBuyAndSelStockThree();
        System.out.println(test.maxProfit(new int[]{1,4,2}));
    }
}
