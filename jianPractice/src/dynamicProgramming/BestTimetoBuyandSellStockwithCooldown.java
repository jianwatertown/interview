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


 Answer:

 Key is to model stage i, then find out stage (i+1)

 // 0.
 buy[i] means before day i what is the maxProfit for *any* sequence end with buy.

 sell[i] means before day i what is the maxProfit for *any* sequence end with sell.

 rest[i] means before day i what is the maxProfit for *any* sequence end with rest.

 // 1.
buy[i] = max(buy[i-1] /do nothing/, rest[i-1] - price /buy now/);
sell[i] = max(sell[i-1] /do nothing/, buy[i-1] + price /sell now/) -> made sure sell after buy
rest[i] = max(sell[i-1],buy[i-1],rest[i-1])

 // 2. because rest[i]>=buy[i]
 rest[i] = max(sell[i-1],buy[i-1],rest[i-1]) = max(sell[i-1],rest[i-1])
 so rest was never after "buy", therefore, we don't need to worry about the state before
 buy[i] = max(buy[i-1] /do nothing/, rest[i-1] - price /buy now/);

 // 3. rest[i] < sell[i] ->  max(sell[i-1],rest[i-1]) = sell[i-1]
 buy[i] = max(buy[i-1] /do nothing/, rest[i-1] - price /buy now/);
 sell[i] = max(sell[i-1] /do nothing/, buy[i-1] + price /sell now/)
 rest[i] = sell[i-1]

 // 4. remove rest
 buy[i] = max(buy[i-1], sell[i-2] - price)
 sell[i] = max(sell[i-1],buy[i-1])

 // 5. then move on

 */
public class BestTimetoBuyandSellStockwithCooldown {

    // TODO the followoing code is incomplete
    // TODO just read this again https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit(int[] prices) {

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
