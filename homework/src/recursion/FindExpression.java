package recursion;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FindExpression {

	
	public boolean getGetExpression(String digits, Integer result){
		Set<Integer> possibleValues = getPossibleValues(digits,new HashMap<String,Set<Integer>>());
		return possibleValues.contains(result);
	}
	
	public Set<Integer> getPossibleValues(String input, Map<String,Set<Integer>> cache){

		Set<Integer> result = new HashSet<Integer>();
		
		// 1. base case
		if(input==null || input.isEmpty()){
			return result;
		}
		
		// 2. find cache
		if(cache.containsKey(input)){
			return cache.get(input);
		}
		
		// 3. itself
		result.add(Integer.parseInt(input));
		
		// 4. slice it
		for(int i=1;i<input.length();i++){
			String prefix  = input.substring(0,i); // 0->i-1 i is not included
			String surfix = input.substring(i,input.length()); // i->length-1
			result.add(Integer.parseInt(prefix)+Integer.parseInt(surfix));
			result.add(Integer.parseInt(prefix)*Integer.parseInt(surfix));
		}
		return result;
	}	
	
	public static void main(String[] args){
		FindExpression ex = new FindExpression();
		System.out.println(ex.getGetExpression("12",3));
		System.out.println(ex.getGetExpression("123",12));
	}
}
