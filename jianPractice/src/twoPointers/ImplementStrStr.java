package twoPointers;

/**
 * Created by jianwang on 4/9/17.
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if(haystack==null||needle==null||haystack.length()<needle.length()) {return -1;}
        for(int h=0;/*exit inside loop*/;h++){
            for(int n=0;/*exit inside loop*/;n++){
                if(n==needle.length()) {return h;}
                if(h+n==haystack.length()) {return -1;} // out of bound
                if(haystack.charAt(h+n)!=needle.charAt(n)) {break;}

            }
        }
    }
}
