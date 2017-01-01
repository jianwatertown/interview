package dynamicProgramming;


/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)


 prices = [1, 2, 3, 0, 2]
 maxProfit = 3
 transactions = [buy, sell, cooldown, buy, sell]

 please read notes here:
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/


 Key is to model stage i, then find out stage (i+1)


 */
public class BestTimetoBuyandSellStockwithCooldown {

    // TODO the followoing code is incomplete
    // TODO ust read this again https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    // TODO key point "One tricky point is how do you make sure you sell before you buy,
    // TODO              since from the equations it seems that [buy, rest, buy] is entirely possible.
    // TODO     Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] = max(sell[i-1], rest[i-1]).
    // TODO     That made sure [buy, rest, buy] is never occurred. "
    // TODO *if we have done our job correctly"
    //
    public int maxProfit(int[] prices) {

        if(prices==null||prices.length<2){
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] rest = new int[prices.length];

        buy[0] = -prices[0];

        for(int i=1;i<prices.length;i++){
            int price  = prices[i];

            // TODO the following code does not work
            // no gaureente we have bought before
            // no gaureente we have not bought before

//            buy[i]  = Math.max(rest[i-1]-price, buy[i-1]);
//            sell[i] = Math.max((buy[i-1]+price, sell[i-1]);
//            rest[i] = Math.max((sell[i-1], buy[i-1], rest[i-1]);

        }
        return Math.max(sell[prices.length-1],rest[prices.length-1]);
    }

    public int maxProfitEditorial(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }
}
