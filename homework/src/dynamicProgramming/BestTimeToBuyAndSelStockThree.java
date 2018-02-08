package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 4/18/17.
 *
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete at most two transactions.

     // try to sell at different i
     // profit[i]  = max(f[i-1] + price[i] - price[j]) for all j (0,i-1)
     //  profit[k][i] when make at most k transactions, and last price index is i, the profit

     // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
     // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
     //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
     // f[0, ii] = 0; 0 times transation makes 0 profit
     // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
 */

public class BestTimeToBuyAndSelStockThree {

    int k = 2;

    public int maxProfitEasy(int[] prices) {
        if(prices==null||prices.length<2) {return 0;}
        int[][] profit = new int[k][prices.length];

        // fill the dp table now
        for(int t=0;t<k;t++){    // for each transaction max
            for(int i=1;i<prices.length;i++){
                for(int p=0;p<=i;p++){
                    int i_p_profit = prices[i]>prices[p]?prices[i]-prices[p]:0;
                    int p_profit =  (t>0 && p>0)? profit[t-1][p-1]:0;
                    profit[t][i] = Math.max(profit[t][p],
                        Math.max(profit[t][i],i_p_profit+p_profit));
                }
            }
        }
        return profit[k-1][prices.length-1];
    }

    // improved, cache the previous max
    // prices[ii] - prices[jj] + f[k-1, jj]) ->  prices[ii] + max(f[k-1, jj] - prices[jj]) is always computed
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<2) {return 0;}
        int[][] profit = new int[k][prices.length];

        // fill the dp table now
        for(int t=0;t<k;t++){                           // for each transaction max
            int preMax = profit[t][0] - prices[0];              // profit - price
            for(int i=1;i<prices.length;i++){           // for each price point
                profit[t][i] = Math.max(profit[t][i-1],prices[i] + preMax       /* sell at i, and buy at previous point*/);
                preMax = Math.max(preMax, (t>0?profit[t-1][i]:0)-prices[i]);    /* now you have seen prices[i], try buying at i*/
            }
        }
        return profit[k-1][prices.length-1];
    }

    public static void main(String[] args){
        BestTimeToBuyAndSelStockThree test = new BestTimeToBuyAndSelStockThree();
        System.out.println(test.maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }
}
