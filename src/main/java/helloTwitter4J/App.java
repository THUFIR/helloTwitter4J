package helloTwitter4J;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Logger;

public class App {

    

       private static final Logger log = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();


	private void loadProperties() {
	}


    public static void main(String[] args)              throws InvalidPropertiesFormatException, IOException {
        new App().loadProperties();
    }


}




