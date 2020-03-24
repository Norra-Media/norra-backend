package com.norra.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AppMsgReaderUtil {
	
	
    private static String path;

    @Value("${app-generic-messages-file-url}")
    public void setPath(String path) {
    this.path = "https://assets.bounce.bike/pool-assets-stage/application-generic-message.yml";
    //this.path = path;
    }

    /**
     * This method reads the file application-generic-message.yml and returns the value corresponding to the key passed
     *
     * @param : key - of type String
     * @return : value - of type String
     */
    public static String getTextValue(String key) {
        String value = null;
        try {
        	log.info("reading yml file from --->"+path);
            URL url = new URL("https://assets.bounce.bike/pool-assets-stage/application-generic-message.yml");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            Properties p = new Properties();
            p.load(reader);
            value = p.getProperty(key);

        } catch (IOException e) {
        	log.info("exception in reading yml file:"+e.getLocalizedMessage());
        }
        return value;
    }
}
