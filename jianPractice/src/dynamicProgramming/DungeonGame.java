package dynamicProgramming;

/**
 * Created by jianwang on 4/24/17.
 *
 *  bottom right to upper left
 *
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon==null||dungeon.length==0||dungeon[0].length==0) {return -1;}
        int h = dungeon.length, w = dungeon[0].length;
        int[][] min = new int[h][w];

        // bottom right to upper left
        for(int i=h-1;i>=0;i--){
            for(int j=w-1;j>=0;j--){
                // bottom right
                if(i==h-1&&j==w-1){min[i][j] = Math.max(1,1-dungeon[i][j]);}
                // bottom row
                else if(i==h-1){min[i][j] = Math.max(Math.max(1,1-dungeon[i][j]),min[i][j+1]-dungeon[i][j]);}
                // right-most column
                else if(j==w-1){min[i][j] = Math.max(Math.max(1,1-dungeon[i][j]),min[i+1][j]-dungeon[i][j]);}
                else{
                    int rightOrDown = Math.min(min[i+1][j]-dungeon[i][j],min[i][j+1]-dungeon[i][j]);
                    min[i][j] = Math.max(Math.max(1,1-dungeon[i][j]),rightOrDown);
                }
            }
        }
        return min[0][0];
    }
}
