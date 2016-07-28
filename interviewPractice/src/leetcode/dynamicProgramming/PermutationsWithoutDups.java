package leetcode.dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class PermutationsWithoutDups {
	
	public Set<String> permutation(String input){

		if(input.length()<=1){
			Set<String> set = new HashSet<String>();
			set.add(input);
			return set;
		}
		
		// 1. find the first character
		char first = input.charAt(0);
		String remaining = input.substring(1,input.length());
		
		// 2. find permutation of substrings
		Set<String> subStringSet = permutation(remaining);
				
		// 3. add the first one to each String in the return set
		Set<String> resultSet = new HashSet<String>();
		for(String sub:subStringSet){
			// add to each slot
			// only length ways, but need length+1 options
			// cannot reach to [0,length-1]
			for(int i=0;i<sub.length();i++){
				StringBuffer sb = new StringBuffer();
				sb.append(sub.substring(0,i));
				sb.append(first);
				sb.append(sub.substring(i,sub.length()));
				resultSet.add(sb.toString());
			}
			resultSet.add(sub+first);
		}
		return resultSet;
	}
	
	public static void main(String[] args){
		PermutationsWithoutDups per = new PermutationsWithoutDups();
		Set<String> result = per.permutation("abc");
		for(String str:result){
			System.out.println(str);
		}
	}
}
