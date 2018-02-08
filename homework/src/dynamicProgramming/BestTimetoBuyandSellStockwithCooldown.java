package dynamicProgramming;


import java.util.Arrays;

/**
        3 states :

    hold[i]:  I have a stock now
    block[i]: I dont have stock and I cannot buy. (after a recent sell)
    empty[i]: I dont have stock and I am ready to buy.

        transitions

    hold[i] = max(hold[i-1],empty[i-1]-prices[i]);
    block[i] = hold[i-1]+prices[i]
    empty[i] = max(empty[i-1],block[i-1])

 */
public class BestTimetoBuyandSellStockwithCooldown {

    public int maxProfit(int[] prices) {

        if(prices==null||prices.length==0){
            return 0;
        }

        int[] hold = new int[prices.length];
        int[] block = new int[prices.length];
        int[] empty = new int[prices.length];

        hold[0] = -prices[0];
        block[0] = 0;
        empty[0] = 0;

        for(int i=1;i<prices.length;i++) {
            hold[i] = Math.max(hold[i-1], empty[i-1] - prices[i]);
            block[i] = hold[i-1] + prices[i];
            empty[i] = Math.max(empty[i-1], block[i-1]);
        }
        return Math.max(Math.max(hold[prices.length-1],empty[prices.length-1]),block[prices.length-1]);
    }
}
