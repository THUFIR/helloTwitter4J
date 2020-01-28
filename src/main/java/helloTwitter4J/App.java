package helloTwitter4J;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import twitter4j.Twitter;

public class App {

    

       private static final Logger log = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();


	private void loadProperties() throws InvalidPropertiesFormatException, IOException {

        properties.loadFromXML(App.class.getResourceAsStream("/twitter.xml"));
        log.info(properties.toString());

        Set<Object> keySet = properties.keySet();
        String key = null;
        String value = null;

        for (Object obj : keySet) {
            key = obj.toString();
            value = System.getenv(key);
            log.info(key+value);
        }
        foo();
	}


    private void foo() {
        Twitter twitter;
      //  twitter = TwitterFactory.getSingleton;
        // Twitter twitter = TwitterFactory.getSingleton();
        // Query query = new Query("source:twitter4j yusukey");
        // QueryResult result = twitter.search(query);
        // for (Status status : result.getTweets()) {
        // log.info("@" + status.getUser().getScreenName() + ":" + status.getText());
        // }

    }

    public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
        new App().loadProperties();
    }


}




