package dynamicProgramming;

import sortAndSearch.BinarySearch;

/**
 * Created by jianwang on 4/29/17.
 */
public class LongestIncreasingSubsequence {

    // Power(n,2) solution
    public int lengthOfLISNN(int[] nums) {

        int max_sequence_length = 0;

        for(int begin=0;begin<nums.length;begin++){
            int last = nums[begin];
            int max = 1;
            for(int end=begin+1;end<nums.length;end++){
                if(last<nums[end]){
                    last = nums[end];
                    max++;
                }
            }
            max_sequence_length = Math.max(max_sequence_length,max);
        }
        return max_sequence_length;
    }

    /**
     * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
     For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

     len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
     len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
     len = 3   :      [4, 5, 6]            => tails[2] = 6

     We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails
     array to find the one needs update.

     Each time we only do one of the two:

     (1) if x is larger than all tails, append it, increase the size by 1
     (2) if tails[i-1] < x <= tails[i], update tails[i]

     always
     1) binary search find an "i" so that either
     (a) tails[i-1] < x <= tails[i]
     or
     (b) last index (when it's largest than all trails)
     2) so tails[i] = x, in both (a), (b) cases
     3) in case (a), max_length++

     Doing so will maintain the tails invariant. The the final answer is just the size.

     * @param nums
     * @return
     */
    // O(nLogN) solution
    public int lengthOfLISFromWeb(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        for(int i=0;i<tails.length;i++){
            System.out.println("i="+i+" "+tails[i]);
        }
        return size;
    }

    // and of the new element element does 2 things
    // 1) if it is bigger than all, then it can add to the longest and make it even longer.
    //	max_length ++;  // can use an index to represent
    //	largest_element_of_length[max_length+1] = largest_element_of_length[max_length].append()
    // 2) otherwise, it must be smaller than one of tail, thus that tail can be updated

    public int lengthOfLIS(int[] nums){

        int max_length_index = 0;
        int[] largest_of_length = new int[nums.length];

        for(int e:nums){

            // binary search to find the equal index or the immediately smaller one
            // largest_of_length[i-1]<nums[i]<=largest_of_length[i] or last element
            // set for both cases
            int indexOfSmallOrEqual = BinarySearch.findTargetIterative(nums, e);
            largest_of_length[indexOfSmallOrEqual] = e;

            // last element
            if(indexOfSmallOrEqual==max_length_index){
                max_length_index++;
            }
        }
        return max_length_index;	// is both the index and the length
    }


    public int lengthOfLISNN2(int[] nums){
        // Base case
        if(nums.length <= 1)
            return nums.length;

        // This will be our array to track longest sequence length
        int T[] = new int[nums.length];

        // Fill each position with value 1 in the array
        for(int i=0; i < nums.length; i++)
            T[i] = 1;


        // Mark one pointer at i. For each i, start from j=0.
        for(int i=1; i < nums.length; i++)
        {
            for(int j=0; j < i; j++)
            {
                // It means next number contributes to increasing sequence.
                if(nums[j] < nums[i])
                {
                    // But increase the value only if it results in a larger value of the sequence than T[i]
                    // It is possible that T[i] already has larger value from some previous j'th iteration
                    if(T[j] + 1 > T[i])
                    {
                        T[i] = T[j] + 1;
                    }
                }
            }
        }

        // Find the maximum length from the array that we just generated
        int longest = 0;
        for(int i=0; i < T.length; i++)
            longest = Math.max(longest, T[i]);

        return longest;
    }
}
