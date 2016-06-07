package leetcode.sorting;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

    Example:
    (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
    (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

    Note:
    You may assume all input has valid answer.
    
    Follow Up:
    Can you do it in O(n) time and/or in-place with O(1) extra space?

	https://leetcode.com/discuss/95156/step-by-step-explanation-of-index-mapping-in-java

  Solution:
  	1) Find the Kth largest element
  	2) Place the remaing elements in such a way that

  	(1) elements smaller than the 'median' are put into the even slots, 
  		from smallest to the biggest one

	(2) elements larger than the 'median' are put into the odd slots
		from the biggest one to the smallest one
		
	(3) the medians are put into the remaining slots.
	
	Index
	Odd - Big
	Even - Small
	E.g. 		1,2,3,4,4,4,5,6,7
	Find Medium:	1,2,3,4,[4],4,5,6,7

		index   0 1 2 3 4 5 6 7
	Place small:	1 _ 3 _ _ _ _ _
	Place big:	1 _ 3 7 _ 6 _ 5
	Place medium:	1 4 3 7 4 6 4 5
	
	Voila! [vwäˈlä/]
	
	
 * @author jian.wang
 *
 */

public class WiggleSortTwo {
   
}