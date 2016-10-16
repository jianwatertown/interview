package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 	Pull Model
 */
public class MiniTwitter {
	
	// 1. user-friends map friends[jian] = ronaldo :jian is following ronaldo
	Map<Integer,HashSet<Integer>> friendsSetMap = new HashMap<Integer,HashSet<Integer>>();
	
	// 2. user-feeds map
	Map<Integer,List<TNode>> feedsOfUser = new HashMap<Integer,List<TNode>>();
	
	// 3. always increasing
	int order = 0;

    public MiniTwitter() {}

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
    	Tweet t = Tweet.create(user_id, tweet_text);
    	List<TNode> feedList = feedsOfUser.get(user_id);
    	if(feedList==null) {feedList=new LinkedList<TNode>();}
    	feedList.add(0,new TNode(++order,t));
    	feedsOfUser.put(user_id, feedList);
    	return t;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) {    	
    	List<TNode> userFeeds = getTenNode(user_id);
    	return convertNodesToTweets(userFeeds);
    }
    
    public List<TNode> getTenNode(int user_id){
    	List<TNode> userFeeds = feedsOfUser.get(user_id);
    	if(userFeeds!=null){
    		return userFeeds.subList(0, userFeeds.size()>=10?10:userFeeds.size());
    	}
    	else{
    		return new LinkedList<TNode>();
    	}
    }
    
    public List<Tweet> convertNodesToTweets(List<TNode> nodes){
    	List<Tweet> resultTweet = new ArrayList<Tweet>();
    	for(int i=0;i<nodes.size();i++){
    		resultTweet.add(nodes.get(i).tweet);
    	}
    	return resultTweet;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
    	
    	List<TNode> tweets = new LinkedList<TNode>();
    	
    	// 1. add users own tweets
    	tweets.addAll(getTenNode(user_id));
    	
    	// 2. add following tweets, 10 per person
    	if(friendsSetMap.get(user_id)!=null){
        	for(Integer follow: friendsSetMap.get(user_id)){
        		tweets.addAll(getTenNode(follow));
        	}
    	}   
    	
        // 3. sort 
    	Collections.sort(tweets, new SortByOrder());
    	
    	// 4. return top 10, should use K-array merge
    	return convertNodesToTweets(tweets
    			.subList(0, tweets.size()>=10?10:tweets.size()));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
    	HashSet<Integer> following = friendsSetMap.get(from_user_id);
    	if(following==null) {following = new HashSet<Integer>();};
    	following.add(to_user_id);
    	friendsSetMap.put(from_user_id,following);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
    	HashSet<Integer> following = friendsSetMap.get(from_user_id);
    	if(following==null) return;
    	following.remove(to_user_id);
    }

	// wrapper of [timestamp(order),tweet]
	class TNode {
		public int order;
		public Tweet tweet;
		public TNode(int o, Tweet t){
			this.order = o;
			this.tweet = t;
		}
	}
	
	/**
	 * General comparator: 1 a>b, 0 1==b, -1 a<b
	 * In Tweets, bigger order (later time stamp), should be the smallest (natural order) 
	 */
	class SortByOrder implements Comparator{
		@Override
		public int compare(Object o1, Object o2) {
			TNode node1 = (TNode)o1;
			TNode node2 = (TNode)o2;
			return node1.order<node2.order?1:-1;
		}
	}
	
	public static void main(String[] args){
		
		MiniTwitter sys = new MiniTwitter();
		sys.postTweet(1, "msg");
		sys.getNewsFeed(1);
		sys.getTimeline(1);
		sys.follow(2, 1);
		sys.getNewsFeed(2);
		sys.unfollow(2, 1);
		sys.getNewsFeed(2);
	}
}