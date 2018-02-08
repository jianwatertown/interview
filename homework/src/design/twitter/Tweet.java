package design.twitter;

public class Tweet {
    public int id;
    public int user_id;
    public String text;
    
    private Tweet(int id, int user_id, String text){
    	this.id = id;
    	this.user_id = user_id;
    	this.text = text;
    }
    
    public static Tweet create(int user_id, String tweet_text) {
        // This will create a new tweet object,
    	// and auto fill id
    	return new Tweet(100,user_id,tweet_text);
    }
    
    public String toString(){
    	return user_id+" "+ this.text;
    }
}
