package design.trie;

import java.util.Stack;


/**
 * Serialize and deserialize a trie (prefix tree, search on internet for more details).
 *      root
          /
         a
       / | \
      b  c  d
     /       \
	e         f
 * 
 * @author jian.wang
 *
 *  root -> ()
 *	
 *      root
         |
         a	 (a())

		root	(a(b()c()))
         |
         a
	    /|
	   b c

 *
 */
public class TrieSerialization {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a trie which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TrieNode root) {

    	StringBuffer sb = new StringBuffer();
    	// <
    	sb.append("<");
    	
    	
    	for(Character c:root.children.keySet()){
    		// child
    		sb.append(c);
    		
    		// recursion
    		sb.append(serialize(root.children.get(c)));
    	}
    	// >
    	sb.append(">");
    	return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     * 
     * root = (a(b()c()))
     *  		
     *  	  a(b()c())
     * 
     * 
     * 1. parent changes
     * <	: last Node we have seen is the parent 	(children following)
     * >	: last Node is no longer the parent for following children (all seen)
     * 		
     * 2. character node change
     * 		a) create a new node
     * 		b) and it's current parent's child
     */
    public TrieNode deserialize(String data) {
    	
    	if(data==null || data.length()==0){
    		return null;
    	}
    	
    	// 1. the path of parents lead to this
    	Stack<TrieNode> path = new Stack<TrieNode>();
    	
    	// 2. handle root node
    	TrieNode treeRoot = new TrieNode();
    	TrieNode currentNode = treeRoot;

    	// 3. going through the children
    	for(Character c: data.toCharArray()){

    		switch(c){
    			// last Node we have seen is the parent 	(children following)
    			case '<':
    				path.push(currentNode);
    				break;
    			// last Node is no longer the parent for following children (all seen)
    			case '>':
    				path.pop();
    				break;
    			default:
    				currentNode = new TrieNode();
    				path.peek().children.put(c, currentNode);
    		}
    	}
    	return treeRoot;
    }
    
    
    public static void main(String [] args){
    	TrieSerialization ts = new TrieSerialization();
    	TrieNode root = new TrieNode();
    	TrieNode a = new TrieNode();
    	TrieNode b = new TrieNode();
    	TrieNode c = new TrieNode();
    	TrieNode d = new TrieNode();
    	
//    	// 1
//    	root.children.put('a', a);
//    	System.out.println(ts.serialize(root));
//    	
//    	// 2
//    	a.children.put('b', b);
//    	a.children.put('c', c);
//    	System.out.println(ts.serialize(root));
//    	
//    	// 3
//    	c.children.put('d', d);
//    	System.out.println(ts.serialize(root));

    	String input = "<a<>>";
    	TrieNode newTree = ts.deserialize(input);
    	System.out.println(ts.serialize(newTree));
    }
}
