package utils;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader configReaderInstance;
    private static final Properties properties=new Properties();
    private static final String testdataFilePath="src/test/resources/TestData.properties";

    private ConfigReader(){
        try(FileInputStream fileInputStream=new FileInputStream(testdataFilePath)) {
            properties.load(fileInputStream);
        }catch (IOException ioException){
            System.out.println("Failed to load properties file");
        }
    }

    public static ConfigReader getInstance(){
        if(configReaderInstance==null){
            synchronized (ConfigReader.class){
                if (configReaderInstance==null){
                    configReaderInstance=new ConfigReader();
                }
            }
        }
        return configReaderInstance;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        ConfigReader configReader=ConfigReader.getInstance();
        String baseUrl= configReader.getProperty("OrangeHrm.url");
        System.out.println(baseUrl);
    }
}
