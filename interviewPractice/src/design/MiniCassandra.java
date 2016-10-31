package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Cassandra is a NoSQL storage. The structure has two-level keys.

    Level 1: raw_key. The same as hash_key or shard_key.
    Level 2: column_key.
    Level 3: column_value
    raw_key is used to hash and can not support range query. let's simplify this to a string.
    column_key is sorted and support range query. let's simplify this to integer.
    column_value is a string. you can serialize any data into a string and store it in column value.
    
    implement the following methods:
    
    insert(raw_key, column_key, column_value)
    query(raw_key, column_start, column_end) // return a list of entries
 * @author jian.wang
 *
 */
public class MiniCassandra {
	
	
	Map<String,TreeMap<Integer,Column>> compKeys = new HashMap<>();
	
    public MiniCassandra() {
    	compKeys = new HashMap<>();
    }
    
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
    	TreeMap<Integer,Column> rangeMap = findRangeMap(raw_key);
    	if(rangeMap==null){
    		rangeMap = new TreeMap<Integer,Column>();
    	}
		rangeMap.put(column_key, new Column(column_key,column_value));
		compKeys.put(raw_key, rangeMap);
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
    	List<Column> result = new ArrayList<>();
		TreeMap<Integer,Column> rangeMap = findRangeMap(raw_key);
		if(rangeMap!=null){
    		NavigableMap<Integer,Column> inRangeSubMap = rangeMap.subMap(column_start, true,column_end,true);
    		for(Column c:inRangeSubMap.values()){
    			result.add(c);
    		}
		}
		return result;
    }
    
    
    public TreeMap<Integer,Column> findRangeMap(String raw_key){
    	if(compKeys.containsKey(raw_key)){
    		return compKeys.get(raw_key);
    	}
    	return null;
    }
}