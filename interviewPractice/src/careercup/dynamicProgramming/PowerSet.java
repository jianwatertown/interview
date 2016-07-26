package careercup.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class PowerSet {

	public ArrayList<ArrayList<Integer>> getAllSubsets(ArrayList<Integer> input){

		ArrayList<ArrayList<Integer>> resultSet = new ArrayList<ArrayList<Integer>>();
		
		if(input.size()==0){
			resultSet.add(new ArrayList<Integer>());
		}
		else{
			for(int i=0;i<input.size();i++){
				
				// remainingSet = input - input[i]
				ArrayList<Integer> remainingSet = new ArrayList<Integer>();
				remainingSet.addAll(input.subList(0, i));
				if(i<input.size()-1){
    				remainingSet.addAll(input.subList(i+1, input.size()));
				}
				
				// subsets of remainingSet
				ArrayList<ArrayList<Integer>> remainingAllSubset = getAllSubsets(remainingSet);

				// add the i in
				for(ArrayList<Integer> subset:remainingAllSubset){

					// without input[i]
					ArrayList<Integer> withoutSet = new ArrayList<Integer>();
					for(Integer e:subset){
						withoutSet.add(e);
					}
					resultSet.add(withoutSet);
					
					// with input[i]
					subset.add(input.get(i));
					resultSet.add(subset);
				}
			}
			
		}
		return resultSet;
	}
	
	
	public static void main(String[] args){
		PowerSet sub = new PowerSet();
		Integer[] intArray = {1,2};
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
