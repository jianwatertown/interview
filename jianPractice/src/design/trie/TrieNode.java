package design.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TrieNode {
	public NavigableMap<Character, TrieNode> children;
	 public List<Integer> top10;
	 boolean endHere = false;
	 public TrieNode() {
		 children = new TreeMap<Character, TrieNode>();
		 top10 = new ArrayList<Integer>();
	 }
}
