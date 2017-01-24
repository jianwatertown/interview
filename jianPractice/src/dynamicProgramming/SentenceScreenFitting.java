package dynamicProgramming;

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


 Solution: keep
 int[] sentenseEndWithThisWordStart = new int[sentence.length];
 int[] nextRowWordIndex = new int[sentence.length];


 a-b-c-a
 b-c-a-b            <--- knowing this
 c-a-b-c
 a-b-c-a
 b-c-a-b            <--- you can know this
 c-a-b-c
 a-b-c-a
 b-c-a-b


 */
public class SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols){

        int nextWordIndex = 0;
        int completeSentence = 0;

        int[] sentenseEndWithThisWordStart = new int[sentence.length];
        int[] nextRowWordIndex = new int[sentence.length];

        Arrays.fill(sentenseEndWithThisWordStart,-1);   // no entry used
        Arrays.fill(nextRowWordIndex,-1);   // no entry used

        for(int row=0;row<rows;row++){

            // use previously computed result
            if(sentenseEndWithThisWordStart[nextWordIndex]!=-1){
                completeSentence+=sentenseEndWithThisWordStart[nextWordIndex];
                nextWordIndex = nextRowWordIndex[nextWordIndex];
            }else{
                int nextCharIndex = 0;
                int completeThisRow = 0;
                int firstWordIndex = nextWordIndex;

                // until nextWordIndex can fit in
                while(nextCharIndex+sentence[nextWordIndex].length()<=cols){

                    nextCharIndex += sentence[nextWordIndex].length()+1;
                    nextWordIndex++;

                    // count last word
                    if(nextWordIndex==sentence.length){
                        completeThisRow++;
                        nextWordIndex=0;
                    }
                }
                sentenseEndWithThisWordStart[firstWordIndex] = completeThisRow;
                nextRowWordIndex[firstWordIndex] = nextWordIndex;
                completeSentence+=completeThisRow;
            }
        }
        return completeSentence;
    }


    public static void main(String[] args){

        candidates.SentenceScreenFitting tester = new candidates.SentenceScreenFitting();

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
