package dynamicProgramming;


//Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
//
//For example:
//
//Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
//
//Follow up:
//Could you do it without any loop/recursion in O(1) runtime?
	
public class AddDigits {

    // func(123) -> func(12+3)
    public int addDigitsSimple(int num) {
	if(num>=10){

	    return addDigitsSimple(addDigitsSimple(num/10)+addDigitsSimple(num%10));
	}
	else{
	    return num;
	}
    }
    
    // WTF?
    // https://en.wikipedia.org/wiki/Digital_root
    public int addDigits(int num) {
	if(num==0)
	    return 0;
	int behind = (num%9);
	return behind==0?9:behind;
    }
}
