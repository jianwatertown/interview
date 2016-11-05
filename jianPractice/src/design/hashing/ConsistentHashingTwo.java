package design.hashing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class ConsistentHashingTwo {
	
	TreeMap<Integer,Integer> keyMachineMap = new TreeMap<>();
	Random r;
	int n;
	int k;
	
	public ConsistentHashingTwo(int n, int k){
		r = new Random();
		this.n=n;
		this.k=k;
	}
	
    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
    public static ConsistentHashingTwo create(int n, int k) {
        return new ConsistentHashingTwo(n,k);
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        List<Integer> machineId = new ArrayList<>();
        int i=k;
    	while(i>0){
        	int newId = r.nextInt(this.n);
        	if(!keyMachineMap.containsKey(newId)){
        		i--;
        		machineId.add(newId);
        		keyMachineMap.put(newId, machine_id);
        	}
        }
    	return machineId;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {

    	if(keyMachineMap.containsKey(hashcode)){
    		return keyMachineMap.get(hashcode);
    	}
    	
    	Entry<Integer,Integer> higherEntry = keyMachineMap.higherEntry(hashcode);
        if(higherEntry==null){
        	return keyMachineMap.firstEntry().getValue();
        }
        else{
        	return higherEntry.getValue();
        }
    }
    
    public static void main(String[] args){
    	ConsistentHashingTwo two = ConsistentHashingTwo.create(100,3);
    	System.out.println(two.addMachine(1));
    	System.out.println(two.getMachineIdByHashCode(4));
    	System.out.println(two.addMachine(2));
    }
}
