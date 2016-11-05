package design.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
	
	Map<Integer,UserMailBox> allUserMailBoxes = 
			new HashMap<Integer,UserMailBox>();
	
	Map<Integer,HashSet<Integer>> followersMap = 
			new HashMap<Integer,HashSet<Integer>>();

	Map<Integer,HashSet<Integer>> followingMap = 
			new HashMap<Integer,HashSet<Integer>>();
	
	int order = 0;

    public Twitter() {}

    // @param user_id an integer
    // @param tweet a string
    // return a Tweet
    public Tweet postTweet(int user_id, String tweet_text) {
    	
    	Tweet t = Tweet.create(user_id, tweet_text);
    	initUserIfNew(user_id);
    	
    	// send messages to each follower
    	for(Integer follower:this.followersMap.get(user_id)){
    		initUserIfNew(follower);
    		UserMailBox followerMailBox = allUserMailBoxes.get(follower);
    		followerMailBox.addTweetNode(new TNode(++order,t),user_id);
    	}
    	return t;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) { 
    	initUserIfNew(user_id);
    	UserMailBox mailbox = allUserMailBoxes.get(user_id);
    	return convertNodesToTweets(mailbox.getTenFromSender(user_id));
    }
    
    public List<Tweet> convertNodesToTweets(List<TNode> nodes){
    	List<Tweet> resultTweet = new ArrayList<Tweet>();
    	if(nodes!=null){
        	for(int i=0;i<nodes.size();i++){
        		resultTweet.add(nodes.get(i).tweet);
        	}
        }
    	return resultTweet;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by time line
    public List<Tweet> getNewsFeed(int user_id) {
    	
    	List<TNode> tweets = new LinkedList<TNode>();
    	
    	initUserIfNew(user_id);
    	UserMailBox mailBox = allUserMailBoxes.get(user_id);
    	
    	for(Integer following:followingMap.get(user_id)){
    		initUserIfNew(following);
    		tweets.addAll(mailBox.getAllFromSender(following));
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
    	
    	if(from_user_id==to_user_id) return;
    	initUserIfNew(from_user_id);
    	initUserIfNew(to_user_id);
    	
    	// 1. from_user_id -> to_user_id
    	followingMap.get(from_user_id).add(to_user_id);
    	
    	// 2. to_user_id -> from_user_id
    	followersMap.get(to_user_id).add(from_user_id);
    	
    	// 3. add all messages from to_user_id
    	UserMailBox toMailBox = allUserMailBoxes.get(to_user_id);
    	UserMailBox fromMailBox = allUserMailBoxes.get(from_user_id);

    	// 	add every single message
    	for(TNode tN:toMailBox.getAllFromSender(to_user_id)){
    		fromMailBox.addTweetNode(tN, to_user_id);
    	}
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
    	
    	if(from_user_id==to_user_id) return;
    	initUserIfNew(from_user_id);
    	initUserIfNew(to_user_id);
    	
    	followersMap.get(to_user_id).remove(from_user_id);
    	followingMap.get(from_user_id).remove(to_user_id);
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
	
	public void initUserIfNew(int user_id){
		// no mailbox
		if(!allUserMailBoxes.containsKey(user_id)){
			allUserMailBoxes.put(user_id, new UserMailBox());
		}
		// no follower
		if(!followersMap.containsKey(user_id)){
			HashSet<Integer> followers = new HashSet<Integer>();
			followers.add(user_id);
			followersMap.put(user_id, followers);
		}
		// no following
		if(!followingMap.containsKey(user_id)){
			HashSet<Integer> following = new HashSet<Integer>();
			following.add(user_id);
			followingMap.put(user_id, following);
		}
	}
	
	public class UserMailBox{
		
		Map<Integer,TreeSet<TNode>> mailPerUser = 
				new HashMap<Integer,TreeSet<TNode>>();
		
		public void addTweetNode(TNode n, int sender){
			TreeSet<TNode> mailFromSender = mailPerUser.get(sender);
			if(mailFromSender==null){
				mailFromSender = new TreeSet<TNode>(new SortByOrder());
				mailPerUser.put(sender, mailFromSender);
			}
			mailFromSender.add(n);
		}
		
		public List<TNode> getTenFromSender(int sender){
			List<TNode> mailFromSender = new LinkedList<TNode>();
			if(mailPerUser.get(sender)!=null){
    			for(TNode n:mailPerUser.get(sender)){
    				mailFromSender.add(n);
    			}
			}
    		return mailFromSender.subList(0, 
    				mailFromSender.size()>=10?10:mailFromSender.size());
		}
		
		public TreeSet<TNode> getAllFromSender(int sender){
			TreeSet<TNode> mailFromSender = mailPerUser.get(sender);
			return mailFromSender==null?new TreeSet<TNode>(new SortByOrder()):mailFromSender;
		}
	}
	
	public static void main(String[] args){
		
		Twitter sys = new Twitter();
		sys.postTweet(1, "LintCode is Good!!!");
		sys.getNewsFeed(1);
		sys.getTimeline(1);
		sys.follow(2, 1);
		sys.follow(2, 3);
		sys.postTweet(3, "LintCode is Cool!!!");
		sys.postTweet(3, "How to do A + B problem?");
		sys.postTweet(3, "I have accepted A + B problem.");
		sys.postTweet(3, "I favorite A + B problem.");
		sys.postTweet(1, "I favorite A + B problem too.");
		sys.postTweet(2, "Nani?");
		sys.postTweet(2, "I want to solve this problem now.");
		sys.postTweet(3, "I want to solve this problem now.");
		sys.postTweet(3, "The problem is so easy.");
		sys.postTweet(1, "hehe.");
		sys.postTweet(2, "Let's to do it together.");
		sys.postTweet(2, "haha");
		sys.getNewsFeed(2);
		sys.getTimeline(2);
		sys.getNewsFeed(1);
		sys.follow(1, 2);
		sys.getNewsFeed(1);
		sys.follow(1, 3);
		sys.getNewsFeed(1);
		sys.unfollow(2, 1);
		sys.getNewsFeed(2);
	}
}