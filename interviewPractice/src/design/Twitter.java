package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 	Push Model
 * 
 *  the following does not work when A follows B and expects to see all B's previous comments.
 *  
 *  Test case input:
 *  postTweet(1, "LintCode is Good!!!")
    getNewsFeed(1)
    getTimeline(1)
    follow(2, 1)
    getNewsFeed(2)
    unfollow(2, 1)
    getNewsFeed(2)
 * 
 * 	output:
 * 	1
    [1]
    [1]
    []
    []
 * 
 * expected:
  * 1
    [1]
    [1]
    [1]
    []
 */
public class Twitter {
	
	Map<Integer,TweetsToOneUser> tweetsToAllUsers = 
			new HashMap<Integer,TweetsToOneUser>();
	
	Map<Integer,HashSet<Integer>> followingMe = 
			new HashMap<Integer,HashSet<Integer>>();

	Map<Integer,HashSet<Integer>> following = 
			new HashMap<Integer,HashSet<Integer>>();
	
	int order = 0;

    public Twitter() {}

    // @param user_id an integer
    // @param tweet a string
    // return a Tweet
    public Tweet postTweet(int user_id, String tweet_text) {
    	
    	Tweet t = Tweet.create(user_id, tweet_text);

    	// a.  find all followers, including myself
    	Set<Integer> followers = this.followingMe.get(user_id);
    	if(followers==null) {
    		followers= new HashSet<Integer>();
    		followers.add(user_id);
    	}
    	
    	// b. send messages to each follower
    	for(Integer follower:followers){
    		// 1. all tweets to this user
    		TweetsToOneUser tweetsToUser = tweetsToAllUsers.get(follower);
    		
    		// 2. no tweets at all? create one
    		if(tweetsToUser==null) {
    			tweetsToUser = new TweetsToOneUser();
        		tweetsToAllUsers.put(follower, tweetsToUser);
    		}
    		
    		// 3. put this tweet after the name of user_id
    		tweetsToUser.addTweetNode(new TNode(++order,t),user_id);    		
    	}
    	return t;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) {    	
    	TweetsToOneUser tweetsToUser = tweetsToAllUsers.get(user_id);
    	if(tweetsToUser!=null){
    		return convertNodesToTweets(tweetsToUser.getTenFromUser(user_id));
    	}
    	return new LinkedList<Tweet>();
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
    // and sort by time line
    public List<Tweet> getNewsFeed(int user_id) {
    	
    	List<TNode> tweets = new LinkedList<TNode>();
    	TweetsToOneUser tweetsToOneUser = tweetsToAllUsers.get(user_id);
    	Set<Integer> pplImFollowing = following.get(user_id);
    	
    	// 1. sad
    	if(pplImFollowing==null) {pplImFollowing = new HashSet<Integer>();}
    	
    	// 2. add myself and all my following
    	pplImFollowing.add(user_id);
    	if(tweetsToOneUser!=null){
        	for(Integer bigV:pplImFollowing){
        		tweets.addAll(tweetsToOneUser.getTenFromUser(bigV));
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
    	
    	// 1. add following me
    	HashSet<Integer> a = followingMe.get(to_user_id);
    	if(a==null) {a = new HashSet<Integer>();};
    	a.add(from_user_id);
    	
    	// 2. add following
    	HashSet<Integer> b = following.get(from_user_id);
    	if(b==null) {b = new HashSet<Integer>();};
    	b.add(to_user_id);
    	
    	// 3. add Big V's previous messages 
    	TweetsToOneUser tweetsToBig = tweetsToAllUsers.get(to_user_id);

    	// when BigV has messages to herself
    	if(tweetsToBig!=null && tweetsToBig.getAllFromUser(to_user_id)!=null){
    		
    		// create queue
    		TweetsToOneUser tweetsToFrom = tweetsToAllUsers.get(from_user_id);
        	if(tweetsToFrom==null){
        		tweetsToFrom = new TweetsToOneUser();
        		tweetsToAllUsers.put(from_user_id, tweetsToFrom);
        	}
        	
        	// add every single message
        	for(TNode tN:tweetsToBig.getAllFromUser(to_user_id)){
        		tweetsToFrom.addTweetNode(tN, to_user_id);
        	}	
    	}
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
    	
    	// 1. add following me
    	HashSet<Integer> a = followingMe.get(to_user_id);
    	if(a!=null) {a.remove(from_user_id);};
    	
    	
    	// 2. add following
    	HashSet<Integer> b = following.get(from_user_id);
    	if(b!=null) {b.remove(to_user_id);};
    	
    	// 3. clear sent messages
    	TweetsToOneUser tweetsToOneUser = tweetsToAllUsers.get(from_user_id);
    	if(tweetsToOneUser!=null){
    		tweetsToOneUser.clearFollowingQueue(from_user_id);
    	}
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
	
	public class TweetsToOneUser{
		Map<Integer,LinkedList<TNode>> nodesPerUser = 
				new HashMap<Integer,LinkedList<TNode>>();
		
		public void addTweetNode(TNode n, int bigV){
			LinkedList<TNode> nodesFromBigV = nodesPerUser.get(bigV);
			if(nodesFromBigV==null){
				nodesFromBigV = new LinkedList<TNode>();
				nodesPerUser.put(bigV, nodesFromBigV);
			}
			nodesFromBigV.add(0,n);
		}
		
		public void clearFollowingQueue(int bigV){
			if(nodesPerUser.get(bigV)!=null){
				nodesPerUser.remove(bigV);
			}
		}
		
		public List<TNode> getTenFromUser(int bigV){
			List<TNode> nodesFromBigV = getAllFromUser(bigV);
    		return nodesFromBigV.subList(0, nodesFromBigV.size()>=10?10:nodesFromBigV.size());
		}
		
		public List<TNode> getAllFromUser(int bigV){
			LinkedList<TNode> nodesFromBigV = nodesPerUser.get(bigV);
			return nodesFromBigV==null?new LinkedList<TNode>():nodesFromBigV;
		}
	}
	
	public static void main(String[] args){
		
		Twitter sys = new Twitter();
		sys.postTweet(1, "msg");
		sys.getNewsFeed(1);
		sys.getTimeline(1);
		sys.follow(2, 1);
		sys.getNewsFeed(2);
		sys.unfollow(2, 1);
		sys.getNewsFeed(2);
	}
}