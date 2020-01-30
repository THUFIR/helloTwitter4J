package helloTwitter4J;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App {

    private static final Logger log = Logger.getLogger(App.class.getName());
    //private final Properties properties = new Properties();

    private Properties loadProperties() throws InvalidPropertiesFormatException, IOException {
        Properties properties = new Properties();
        properties.loadFromXML(App.class.getResourceAsStream("/twitter.xml"));
        log.fine(properties.toString());

        Set<Object> keySet = properties.keySet();
        String key = null;
        String value = null;

        for (Object obj : keySet) {
            key = obj.toString();
            value = System.getenv(key);
            log.fine(key + value);
            properties.setProperty(key, value);
        }
        return properties;
    }

    private void runQuery() throws TwitterException, InvalidPropertiesFormatException, IOException {
        Properties properties = loadProperties();
        log.info(properties.toString());  //this matches what powershell uses
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getProperty("oAuthConsumerKey"))
                .setOAuthConsumerSecret(properties.getProperty("oAuthConsumerSecret"))
                .setOAuthAccessToken(properties.getProperty("oAuthAccessToken"))
                .setOAuthAccessTokenSecret(properties.getProperty("oAuthAccessTokenSecret"));

        //Twitter twitter = new TwitterFactory(configurationBuilder.build()).getSingleton();

        TwitterFactory factory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = factory.getInstance();

        // Twitter twitter = new TwitterFactory(configurationBuilder.build()).getSingleton();
        //   twitter. //       TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build);
        //   Twitter twitter = TwitterFactory.getSingleton();
//        Query query = new Query("source:twitter4j yusukey");
  //      QueryResult result = twitter.search(query);
        //   for (Status status : result.getTweets()) {
        //       log.info("@" + status.getUser().getScreenName() + ":" + status.getText());
        //    }

    }

    public static void main(String[] args) throws InvalidPropertiesFormatException, IOException, TwitterException {
        new App().runQuery();
    }

}
