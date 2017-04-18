package dynamicProgramming;

/**
 * Created by jianwang on 4/15/17.
 */
public class ZigZagSubsequence {
    int longestZigZag(int[] sequence){
        int len = sequence.length;
        int pSize = 0;
        int nSize = 0;
        int p = Integer.MAX_VALUE;      // last digit of the sequence whose diff is +
        int n = Integer.MIN_VALUE;      // last digit of the sequence whose diff is -
        for(int num:sequence){
            int pSizeNew = Math.max(num>n?nSize+1:0,pSize);
            int nSizeNew = Math.max(num<p?pSize+1:0,nSize);
            int p2 = num>n?num:p;
            int n2 = num<p?num:n;
            p = p2;
            n = n2;
            pSize = pSizeNew;
            nSize = nSizeNew;
        }
        return Math.max(pSize,nSize);
    }

    public static void main(String[] args){
        ZigZagSubsequence t = new ZigZagSubsequence();
        System.out.println(t.longestZigZag(new int[]{ 1, 7, 4, 9, 2, 5 }));
        System.out.println(t.longestZigZag(new int[]
                { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }
        ));
        System.out.println(t.longestZigZag(new int[]
                { 44}
        ));
        System.out.println(t.longestZigZag(new int[]
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }
        ));
        System.out.println(t.longestZigZag(new int[]
        { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }
        ));
        System.out.println(t.longestZigZag(new int[]
                { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }
        ));
    }
}
