package org.camunda.bpm.example.twitter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Publish content on Twitter. It really goes live! Watch out http://twitter.com/#!/camunda_demo for your postings.
 */
public class TweetContentDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {      
    Tweet tweet = (Tweet) execution.getVariable("tweet");
      
    AccessToken accessToken = new AccessToken("220324559-8hWDVUXMSOaAnmtNNwBuNuhGJ6hOGwNdWHqhdOsU", "sGBZafB7saWYMwnPQGjjwU9Ggr0IJYkmPdyAFU5PI");
    Twitter twitter = new TwitterFactory().getInstance();
    twitter.setOAuthConsumer("HW62M0Rxtt39CbgdgP1og", "TJHNqolmPD6aHPtX8ec5Xp5zgIJcsMBTkwMpGCqdGuk");
    twitter.setOAuthAccessToken(accessToken);
    
    twitter.updateStatus(tweet.getContent());
  }

}
