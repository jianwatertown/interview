package dynamicProgramming;

/**
 * Created by jianwang on 12/30/16.
 */
public class CountingBits {

    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }
}
