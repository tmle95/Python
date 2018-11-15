import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;

public class PostTwitter
{
  private Twitter twitter;
  private TwitterFactory tf;
  private List<Status> status;
  ConfigurationBuilder cb = new ConfigurationBuilder();
  Properties prop;
  // FileInputStream in;

  public PostTwitter() throws IOException {
    status = new ArrayList<Status>();

    // Load the twitter api keys
    prop = new Properties();
    InputStream in = new FileInputStream("../../../secrets_CTC.prop");

    prop.load(in);
    prop.list(System.out);

    cb.setDebugEnabled(true)
            .setOAuthConsumerKey(prop.getProperty("consumerKey"))
            .setOAuthConsumerSecret(prop.getProperty("consumerSecret"))
            .setOAuthAccessToken(prop.getProperty("accessToken"))
            .setOAuthAccessTokenSecret(prop.getProperty("accessTokenSecret"));

    tf = new TwitterFactory(cb.build());
    in.close();
  }

  public void TweetOnTwitter(String output) throws TwitterException{
    twitter = tf.getInstance();
    Status status = twitter.updateStatus(output);
    System.out.println("Successfully tweet on Twitter!");
    System.out.println(output);
  }
}
