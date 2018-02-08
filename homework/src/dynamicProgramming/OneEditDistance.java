package dynamicProgramming;

public class OneEditDistance {
    
	public boolean isOneEditDistance(String s, String t) {

		// setup 
		if(s==null || t==null || Math.abs(s.length()-t.length())>1) {return false;}
		String a = s.length()<=t.length()?s:t;
		String b = s.length()>t.length()?s:t;

		for(int i=0;i<a.length();i++){
			// find first different
			if(a.charAt(i)!=b.charAt(i)){	
				// 1. has to be update
				if(a.length()==b.length()){
					return a.substring(i+1).equals(b.substring(i+1));
				}
				// 2. add to a, aka skip current b, delete b, aka skip current b
				else{
					return a.substring(i).equals(b.substring(i+1));					
				}
			}
		}
		// no different found, either they are the same or only 1 character apart
		return b.length()!=a.length();	
	}

	// super cool
	public boolean isOneEditDistanceIterative(String s, String t) {

		if(s==null || t==null || Math.abs(s.length()-t.length())>1) {return false;}
		int i=0, len = Math.min(s.length(),t.length());

		// exit only when all elements in s[0:i] matches t[0:i]
		while(i<len){
			if(s.charAt(i)!=t.charAt(i)) {
				String remainS = i<s.length()-1?s.substring(i+1):"";
				String remainT = i<t.length()-1?t.substring(i+1):"";
				return (remainS.equals(remainT) || (t.substring(i).equals(remainS)) || s.substring(i).equals(remainT));
			}
			i++;
		}
		return s.length()!=t.length();
	}
}


