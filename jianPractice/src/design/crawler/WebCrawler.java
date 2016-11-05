package design.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 * Implement a webpage Crawler to crawl webpages of http://www.wikipedia.org/. 
 * To simplify the question, let's use url instead of the the webpage content.

    Your crawler should:
    
    Call HtmlHelper.parseUrls(url) to get all urls from a webpage of given url.
    Only crawl the webpage of wikipedia.
    Do not crawl the same webpage twice.
    Start from the homepage of wikipedia: http://www.wikipedia.org/

    Given
    
    "http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
    "http://www.wikipedia.org/help/": []
    Return ["http://www.wikipedia.org/", "http://www.wikipedia.org/help/"]
    
    Given:
    
    "http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
    "http://www.wikipedia.org/help/": ["http://www.wikipedia.org/", "http://www.wikipedia.org/about/"]
    "http://www.wikipedia.org/about/": ["http://www.google.com/"]
    Return ["http://www.wikipedia.org/", "http://www.wikipedia.org/help/", "http://www.wikipedia.org/about/"]
 * @author jian.wang
 *
 */
public class WebCrawler {
    public List<String> crawler(String url) {
    	
    	// 1. bfs setup
    	Set<String> urlSeen = new HashSet<>();
    	Queue<String> url2Visit = new LinkedList<>();
    	url2Visit.add(url);
        
    	while(!url2Visit.isEmpty()){
    		
    		// 2. visit it
    		String cUrl = url2Visit.poll();
    		urlSeen.add(cUrl);
    		System.out.println("processing "+cUrl);
    		
    		// 3. visit its children
    		String content = getContentFromUrl(cUrl);
    		List<String> nextBatch = HtmlHelper.parseUrls(content);
    		for(String urlChild:nextBatch){
    			if(!urlSeen.contains(urlChild) && urlChild.contains("wikipedia.com")){
    				url2Visit.add(urlChild);
    			}
    		}
    	}
    	return new ArrayList(urlSeen);
    }
    
	// https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    public String getContentFromUrl(String urlStr){
    	StringBuffer sb = new StringBuffer();
    	try{
            URL url = new URL(urlStr);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
    
            String inputLine;
            
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
    	}catch(Exception dontCare){System.out.println(dontCare);}
        return sb.toString();
    }
    
    public static void main(String[] args){
    	WebCrawler crawler = new WebCrawler();
    	String url = "https://www.wikipedia.org/";
    	for(String str:crawler.crawler(url)){
    		System.out.println(str);
    	}
    }
}
