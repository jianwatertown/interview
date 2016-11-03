package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;


// pick first, recursion
public class PowerSet {

	public ArrayList<ArrayList<Integer>> getAllSubsets(ArrayList<Integer> input){

		ArrayList<ArrayList<Integer>> resultSet = new ArrayList<ArrayList<Integer>>();
		
		// 1. empty set
		if(input.size()==0){
			resultSet.add(new ArrayList<Integer>());
		}
		else{
			// 2. pick first element
			Integer first = input.get(0);
			
			// 3. get remains
			ArrayList<Integer> setWithoutFirst = new ArrayList<Integer>();
			if(input.size()>1){
				setWithoutFirst.addAll(input.subList(1, input.size()));
			}
			
			// 4. subsets of second part
			ArrayList<ArrayList<Integer>> remainingAllSubset = getAllSubsets(setWithoutFirst);

			// 5. add i or not
			for(ArrayList<Integer> subset:remainingAllSubset){

				// without input[i]
				resultSet.add(new ArrayList<Integer>(subset));
				
				// with input[i]
				subset.add(first);
				resultSet.add(subset);
			}
			
		}
		return resultSet;
	}
	
	
	public static void main(String[] args){
		PowerSet sub = new PowerSet();
		Integer[] intArray = {1,2,3};
		ArrayList<ArrayList<Integer>> subsets = sub.getAllSubsets(new ArrayList<Integer>(Arrays.asList(intArray)));
		for(ArrayList<Integer> set:subsets){
			for(Integer i:set){
				System.out.print(" "+i);
			}
			System.out.println();
			System.out.println("==========");
		}
	}
}
