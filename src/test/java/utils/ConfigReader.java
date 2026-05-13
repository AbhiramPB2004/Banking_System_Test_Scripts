package utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private  final Properties properties;


    //constructor (initialize Properties object)
    public ConfigReader() throws IOException {
        FileInputStream fs = new FileInputStream("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\java\\resources\\config\\API_CONFIG.prop");
        this.properties = new Properties();
        this.properties.load(fs);
    }

    //fetch and return value from config File
    public String getProp(String key){
        return this.properties.getProperty(key);
    }

}
