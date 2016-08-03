package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildOrder {
	
	public void printBuildOrder(List<Pair> pairs, char[] projects){
		
		Map<Character,GraphNode> rootMap = new HashMap<Character,GraphNode>();
		Map<Character,GraphNode> map = new HashMap<Character,GraphNode>();
		
		// 1. build dependency graph
		for(Pair pair: pairs){
					
			GraphNode child = getOrCreateNode(pair.child,map);
			GraphNode parent;
			
			// first time seen a parent, must being a parent
			if(!map.containsKey(pair.parent)){
				parent = new GraphNode(pair.parent);
				map.put(pair.parent, parent);
				rootMap.put(pair.child,parent);
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
		List<GraphNode> buildorder = new LinkedList<GraphNode>();
		Set<Character> mySet = getNodeSet(projects);
	
		// 3. go through the list of map and find out stuff
		// TODO
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
		Character[] projects = {'a','b','c','d','e','f'};
		
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);
		pairs.add(p4);
		pairs.add(p5);
		
		BuildOrder build = new BuildOrder();
		build.printBuildOrder(pairs, null);
	}	
}
