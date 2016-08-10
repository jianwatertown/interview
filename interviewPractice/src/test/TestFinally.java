package test;

public class TestFinally {

	public String testGetFinanllyString(){
		try{
			return printAndReturn();
		}
		finally{
			// executed before the *final* result is returned
			System.out.println("Inside finally");

		}
	}
	
	public String printAndReturn(){
		String msg = "Returning from printAndReturn";
		System.out.println(String.format("If you see the next line is not \"%s\", then finally is excuted",msg));
		return msg;
	}
	
	public static void main(String[] args){
		TestFinally test = new TestFinally();
		System.out.println(test.testGetFinanllyString());
	}
}
