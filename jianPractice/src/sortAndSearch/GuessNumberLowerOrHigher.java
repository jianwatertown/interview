package sortAndSearch;

/**
 * Created by jianwang on 1/1/17.
 *
 * We are playing the Guess Game. The game is as follows:

     I pick a number from 1 to n. You have to guess which number I picked.

     Every time you guess wrong, I'll tell you whether the number is higher or lower.

     You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

     -1 : My number is lower
     1 : My number is higher
     0 : Congrats! You got it!
     Example:
     n = 10, I pick 6.

 Return 6.


    The guess API is defined in the parent class GuessGame.
 @param num, your guess
 @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
 int guess(int num);

 */
public class GuessNumberLowerOrHigher {

    public int guessNumber(int n) {
        int s = 1;
        int e = n;
        while(s<=e){

            // watch, if you use (s+e)/2 will give you out of boudnary problem
            int mid = s+(e-s)/2;

            int answer = guess(mid);
            if(answer==0){
                return mid;
            }
            else if(answer>0){ // need go higher
                s = mid+1;
            }
            else{              // need go lower
                e = mid-1;
            }
        }
        return -1;
    }

    public int guess(int num){
        return 0;
    }
}
