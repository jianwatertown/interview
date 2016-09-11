package bit;

// bitwise 
// & and
// | or
// ^ XOR exclusive or				
// ~ negative
// << left shift
// >> right shift
// >>> zero fill right shift

// integer 32 bit - 00000000-00000000-00000000-00000000
public class BitWiseStudy {
	

	public static void testBasicOperations(){
		int two = 2;		//  10
		int one = 1;		//  01
		int three = 3;		//  11
		
		print("2&1",(two&one));	// 0 	
		print("2|1",(two|one));	// 3
		print("3^1",(three^one));	// 2
		print("2<<3",(two<<3));	// 16
		print("3>>1",(3>>one));	// 1
		print("3>>>2",(three>>>2)); // 0 ,right shift 2 times, adding 0 zero
	}
	
	public static void print(String prefix, int value){
		System.out.println(prefix+ " "+String.valueOf(value));
	}
	
	public static void testHexDecimal(){
		// 0x - hex
		// a-f(or A-F)
        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
		
	}
	
    public static void main(String[] args) {
    	testHexDecimal();
    	testBasicOperations();
    }
}
