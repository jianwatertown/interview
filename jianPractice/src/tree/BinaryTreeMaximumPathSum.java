package tree;

public class BinaryTreeMaximumPathSum {
	
    public int maxPathSum(TreeNode root) {
        return helper(root).maxPathSum;
    }

    public PathSumPair helper(TreeNode r){
    	
        // 1. base case 
        if(r==null) {return new PathSumPair(0,0);};    
        
        // 2. left path
        PathSumPair maxLeft = helper(r.left);       // (2,2)
        
        // 3. right path
        PathSumPair maxRight = helper(r.right);     // (10,10)
        
        // 4. compute the max
    
        int longetPath = (maxLeft.longestPath>maxRight.longestPath?maxLeft.longestPath:maxRight.longestPath) + r.val; // 0
        
        int maxOfTwoSubTree = Math.max(maxLeft.maxPathSum,maxLeft.maxPathSum); // 10
        
        int pathSumGoingThroughtRoot = (maxLeft.longestPath+maxRight.longestPath)+r.val; // 2
        
        
        int max = r.val;
        max = Math.max(max, Math.max((pathSumGoingThroughtRoot<0?r.val:pathSumGoingThroughtRoot),maxOfTwoSubTree));
        return new PathSumPair(max,longetPath);
    }
    
    public class PathSumPair {
        int maxPathSum;
        int longestPath;
        
        public PathSumPair(int maxPathSum,int longestPath){
            this.maxPathSum = maxPathSum;
            this.longestPath =longestPath;
        }
    }
}
