package forloop;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if(s==null||t==null||s.length()!=t.length()) {return false;}
        Map<Character,Integer> index = new HashMap<>();
        for(Integer i=0;i<t.length();i++){
            System.out.println(index.get(s.charAt(i))+" "+index.get(t.charAt(i)));
            if(index.put(s.charAt(i),i)!=(index.put(t.charAt(i),i)))
            {
             //   System.out.println(index.get(s.charAt(i))+" "+index.get(t.charAt(i)));
                return false;
            }
        }
        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    public static void main(String[] args){
        IsomorphicStrings test = new IsomorphicStrings();
     //   System.out.println(test.isIsomorphic("a","a"));
        System.out.println(null==null);
        System.out.println(null!=null);
     //   System.out.println(test.wordPattern("a","a"));
    }
}
