package dynamicProgramming;

/**
 *
 * Write a program to check whether a given number is an ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

 Note that 1 is typically treated as an ugly number.
 * Created by jianwang on 1/1/17.
 */
public class UglyNumber {

    public boolean isUgly(int num) {
        int[] prime_factor = new int [] {2,3,5};
        if(num>0){
            for(int prime:prime_factor){
                while(num % prime ==0){
                    num = num / prime;
                }
            }
        }
        return num==1;
    }
}
