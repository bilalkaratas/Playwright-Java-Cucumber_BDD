package core.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);
    private static Properties properties;

    static {
        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            log.error("Error reading configuration file with exception :" + e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
