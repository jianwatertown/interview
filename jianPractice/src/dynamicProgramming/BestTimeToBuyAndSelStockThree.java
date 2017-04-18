package dynamicProgramming;

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

        if(prices==null||prices.length<0) {return 0;}
        int len = prices.length;
        int[] profit = new int[len];
    }

}
