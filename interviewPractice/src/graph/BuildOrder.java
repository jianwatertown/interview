package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


// TODO: this algorithm is *not* working
// as currently, child -> parent
// so when we have the root of the tree - the parent of everybody
// we need to either
// 	1) reconstruct the tree, so parent has a copy of all its children, or
//  2) do a very expensive sweeping traversal in the leaf node set
//
// TODO follow up question, how to invert a tree???


public class BuildOrder {
	
	public void printBuildOrder(List<Pair> pairs, char[] projects){
		
		Map<Character,GraphNode> rootMap = new HashMap<Character,GraphNode>();
		Map<Character,GraphNode> map = new HashMap<Character,GraphNode>();
		List<GraphNode> buildorder = new LinkedList<GraphNode>();
		
		// 1. build dependency graph
		for(Pair pair: pairs){
					
			GraphNode child = getOrCreateNode(pair.child,map);
			GraphNode parent;
			
			// first time seen a parent, must being a parent
			if(!map.containsKey(pair.parent)){
				parent = new GraphNode(pair.parent);
				map.put(pair.parent, parent);
				rootMap.put(pair.parent,parent);
			}
			
			else{
				parent = map.get(pair.parent);
			}
			
			child.dependsOn = parent;
			rootMap.remove(pair.child);
			
		}
		
		for(Character n:rootMap.keySet()){
			System.out.println(n);
		}
		
		// 2. now generate sequence
		Set<Character> projectsSet = getNodeSet(projects);
	
		// 3. go through the list of map and find out stuff
		for(GraphNode root:rootMap.values()){
			DFS(root,buildorder,projectsSet);
		}
		
		// 4. print the set
		printLinkedList(buildorder);
	}
	
	public void printLinkedList(List<GraphNode> resultList){
		for(GraphNode n:resultList){
			System.out.println(n.value+" ");
		}
	}
	
	public void DFS(GraphNode root, List<GraphNode> resultList,Set<Character> projectSet){
		if(root!=null){
			// only add to the list when we have not seen this before
			if(projectSet.contains(root.value)){
				resultList.add(root);
				projectSet.remove(root.value);
			}
			DFS(root.dependsOn,resultList,projectSet);
		}
	}
	
	public Set<Character> getNodeSet(char[] projects){
		Set<Character> mySet = new HashSet<Character>();
		for(char project:projects){
			mySet.add(project);
		}
		return mySet;
	}
	
	
	public GraphNode getOrCreateNode(Character c, Map<Character,GraphNode> map){
		if(!map.containsKey(c)){
			map.put(c, new GraphNode(c));
		}
		return map.get(c);
	}
	
	class GraphNode{
		Character value;
		GraphNode dependsOn;
		public GraphNode(Character c){
			this.value = c;
		}
	}
	
	public static void main(String[] args){
		
		Pair p1 = new Pair('d','a');
		Pair p2 = new Pair('b','f');
		Pair p3 = new Pair('d','b');
		Pair p4 = new Pair('a','f');
		Pair p5 = new Pair('c','d');
		
		List<Pair> pairs = new ArrayList<Pair>();
		char[] projects = {'a','b','c','d','e','f'};
		
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);
		pairs.add(p4);
		pairs.add(p5);
		
		BuildOrder build = new BuildOrder();
		build.printBuildOrder(pairs, projects);
	}	
}
