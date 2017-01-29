package dynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jianwang on 1/28/17.
 *
 *
 * https://en.wikipedia.org/wiki/Minimax
 *
 * http://www.flyingmachinestudios.com/programming/minimax/
 * https://www.youtube.com/watch?v=STjW3eH0Cik
 *
 * https://www.youtube.com/watch?v=xBXHtz4Gbdo
 *
 * What if we change the game so that players cannot re-use integers?
 *
 *
 *  Branching factor: how many branches     (B)
 *  Depth: how many levels                  (D)
 *
 *  "B to the D"
 *
 *  Check moves = 10(120)
 *
 *  Atomics in the 10(80)
 *  Second in a year Pi*10(7)
 *  Years in the universe 10(10)
 *  Nano seconds in second 10(9)
 *  === 10(106)
 *
 *  "Alpha-Beta" is one level on top of MinMax
 *
 *  beautiful!
 *
 */
public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        Map<Integer,Boolean> cached = new HashMap<>();
        int sum = 0;

        if(desiredTotal<=0) {return true;}
        Set<Integer> valuesToUse = new HashSet<>();
        for(int i=1;i<=maxChoosableInteger;i++){
            valuesToUse.add(i);
            sum+=i;
        }
        if(sum<desiredTotal){
            return false;
        }
        return canStepWin(valuesToUse,desiredTotal, cached, 0);
    }

    public boolean canStepWin(Set<Integer> valuesToUse, int desiredTotal, Map<Integer,Boolean> cached, int key){

        if(cached.containsKey(key)){
            return cached.get(key);
        }

        boolean sureToWin = false;

        if(desiredTotal<=0){
            return false;                   // for sure it has lost and no way to win
        }

        for(int value:valuesToUse){

            // use this value now
            Set<Integer> remainingValue = new HashSet(valuesToUse);
            remainingValue.remove(value);

            // check next move
            // use "1001" to represent set (4,1)
            int childKey = key | (1<<value);
            boolean nextMoveWin = canStepWin(remainingValue,desiredTotal-value,cached,childKey);

            // in any situation when next sure to loose, I have a winning strategy
            sureToWin = sureToWin || !nextMoveWin;

            if(sureToWin){
                break;
            }
        }
        cached.put(key,sureToWin);
        return sureToWin;
    }

    public static void main(String[] args){
        CanIWin test = new CanIWin();
        System.out.println(test.canIWin(10,1));
        System.out.println(test.canIWin(4,6));
        System.out.println(test.canIWin(20,210));
        System.out.println(2|5);
    }
}
