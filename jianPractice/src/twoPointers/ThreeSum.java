package twoPointers;

import com.sun.tools.javac.util.List;

import java.util.Arrays;
import java.util.LinkedList;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] num) {
//        Arrays.sort(num);
//        List<List<Integer>> res = new LinkedList>();
//        for (int i = 0; i < num.length-2; i++) {
//            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
//                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
//                while (lo < hi) {
//                    if (num[lo] + num[hi] == sum) {
//                        Integer[] temp = new Integer[]{num[i], num[lo], num[hi]};
//                        res.add(Arrays.asList(temp));
//                        while (lo < hi && num[lo] == num[lo+1]) lo++;
//                        while (lo < hi && num[hi] == num[hi-1]) hi--;
//                        lo++; hi--;
//                    } else if (num[lo] + num[hi] < sum) lo++;
//                    else hi--;
//                }
//            }
//        }
//        return res;
        return null;
    }
}
