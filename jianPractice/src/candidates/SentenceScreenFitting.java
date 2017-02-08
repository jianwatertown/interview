package candidates;

import java.util.Arrays;

/**
 * Created by jianwang on 1/23/17.
 *
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given
 * sentence can be fitted on the screen.

     Note:

     A word cannot be split into two lines.
     The order of words in the sentence must remain unchanged.
     Two consecutive words in a line must be separated by a single space.
     Total words in the sentence won't exceed 100.
     Length of each word is greater than 0 and won't exceed 10.
     1 ≤ rows, cols ≤ 20,000.

     Input:
     rows = 2, cols = 8, sentence = ["hello", "world"]

     Output:
     1

     Explanation:
     hello---
     world---

     The character '-' signifies an empty space on the screen.


     Input:
     rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

     Output:
     2

     Explanation:
     a-bcd-
     e-a---
     bcd-e-

     The character '-' signifies an empty space on the screen.

     Input:
     rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

     Output:
     1

     Explanation:
     I-had
     apple
     pie-I
     had--

     The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols){
        int[] wordSizes = this.wordSizes(sentence);
        int startingIndex = 0;
        int result = 0;
        for(int i = 0; i< rows;i++)
        {
            int previousSum = 0;
            int sum = 0;
            int remainedCols = cols;
            for (int j = startingIndex; ;j++) {
                if(j == wordSizes.length)
                {
                    startingIndex = 0;
                    result ++;
                    break;
                }

                sum = sum + wordSizes[j]+ 1;
                if (sum > remainedCols) {
                    startingIndex = j;
                    remainedCols = cols - previousSum;
                    if(remainedCols <= 0)
                    {
                        remainedCols = cols;
                    }
                    break;
                }
                previousSum = sum;
            }
        }
        return result;
    }

    private int[] wordSizes(String[] sentence)
    {
        int[] result = new int[sentence.length];
        for (int i = 0; i< sentence.length; i++) {
            result[i] = sentence[i].length();
        }

        return result;
    }


    public static void main(String[] args){

        SentenceScreenFitting tester = new SentenceScreenFitting();

        int v1 = tester.wordsTyping(new String[] {"hello","world"}, 2, 8 );
        assert 1==v1:"v1 =" + v1;

        int v2 = tester.wordsTyping(new String[] {"a","bcd","e"}, 3, 6 );
        assert 2==v2:"v2 =" + v2;

        int v3 = tester.wordsTyping(new String[] {"I","had","apple","pie"}, 4, 5);
        assert 1==v3:"v3="+v3;

        int v4 = tester.wordsTyping(new String[] {"a","b","c"}, 8, 7);
        assert 10==v4:"v4="+v4;

    }
}
