package helloTwitter4J;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App {

    private static final Logger log = Logger.getLogger(App.class.getName());

    private Properties loadProperties() throws IOException {
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

    private TwitterFactory configTwitterFactory(Properties properties) throws IOException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getProperty("oAuthConsumerKey"))
                .setOAuthConsumerSecret(properties.getProperty("oAuthConsumerSecret"))
                .setOAuthAccessToken(properties.getProperty("oAuthAccessToken"))
                .setOAuthAccessTokenSecret(properties.getProperty("oAuthAccessTokenSecret"));

        TwitterFactory twitterFactory = null;
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        return twitterFactory;
    }

    private void getHomeTimeLine() throws TwitterException, IOException {
        Twitter twitter = configTwitterFactory(loadProperties()).getInstance();
        List<Status> statuses = null;
        statuses = twitter.getUserTimeline("KimKardashian");
        if (statuses != null) {
            for (Status status : statuses) {
                log.info(status.getUser().getName() + ":" + status.getText());
            }
        }
    }

    public static void main(String[] args) throws TwitterException, IOException {
        new App().getHomeTimeLine();
    }

}
