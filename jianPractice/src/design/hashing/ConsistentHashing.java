package design.hashing;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ConsistentHashing {

	/**
     * @param n a positive integer
     * @return n x 3 matrix
     */
    public List<List<Integer>> consistentHashing(int n) {
        
    	PriorityQueue<HashRange> maxQ = new PriorityQueue<>();
    	
    	// 1. init
    	maxQ.add(new HashRange(0,359,1));
    	
    	// 2. iterate n times
    	for(int i=1;i<n;i++){
    		// 3. poll the max node, split it to two and add two back to queue
    		HashRange rangeToSplit = maxQ.poll();
    		for(HashRange rH: rangeToSplit.splitAndAssignNewIndex(maxQ.size()+2)){
    			maxQ.add(rH);
    		}
    	}    	    	
    	return getHashRangeListFromQueue(maxQ);
    }
    
    public List<List<Integer>> getHashRangeListFromQueue(PriorityQueue<HashRange> maxHeap){
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	while(!maxHeap.isEmpty()){
    		result.add(maxHeap.poll().toListRepresentation());
    	}
    	return result;
    }
    
    
    public class HashRange implements Comparable<HashRange>{
    	public int start;
    	public int end;
    	public int index;
    	
    	public HashRange(int start, int end, int index){
    		this.start = start;
    		this.end = end;
    		this.index = index;
    	}
    	
    	public Integer getSize(){
    		return end-start+1;
    	}
    	
    	public List<Integer> toListRepresentation(){
    		List<Integer> rep = new ArrayList<>();
    		rep.add(this.start);
    		rep.add(this.end);
    		rep.add(this.index);
    		return rep;
    	}
    	
    	//  [a...b...c] --> [a..b] + [b+1....c]
    	public List<HashRange> splitAndAssignNewIndex(int newIndex){
    		List<HashRange> twoTuple = new ArrayList<>();

    		// split in the middle
    		int mid = (start+end)/2;
    		HashRange newRange = new HashRange(mid+1,end,newIndex);
    		this.end = mid;
    		
    		// built new partitions 
    		twoTuple.add(this);
    		twoTuple.add(newRange);    		
    		return twoTuple;
    	}

    	// the bigger size, then smaller index gets -1 (natural order in front)
		@Override
		public int compareTo(HashRange other) {
			if(this.getSize()!=other.getSize()){
				return this.getSize()>other.getSize()?-1:1;
			}
			return this.index<other.index?-1:1;
		}
    }
    
    public static void main(String[] args){
    	ConsistentHashing cH = new ConsistentHashing();
    	for(List l:cH.consistentHashing(5)){
    		System.out.println(l);
    	}
    }
}
