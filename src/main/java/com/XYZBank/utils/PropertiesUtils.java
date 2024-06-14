package com.XYZBank.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    static Properties prop = new Properties();
    static String projectPath = System.getProperty("user.dir");

    public String getProperties(String name) throws IOException {
        InputStream input = new FileInputStream(projectPath + "/src/main/java/com/XYZBank/config/config.properties");
        prop.load(input);
        return prop.getProperty(name);
    }
    public static void updateConfig(String key, String value) {
        Properties properties = new Properties();

        try {
            // Load existing properties from the config file
            FileInputStream input = new FileInputStream(projectPath + "/src/main/java/Output/outputConfig.properties");
            properties.load(input);
            input.close();

            // Update the property value
            properties.setProperty(key, value);

            // Save the updated properties to the config file
            FileOutputStream output = new FileOutputStream(projectPath + "/src/main/java/Output/outputConfig.properties");
            properties.store(output, null);
            output.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getOutputProperties(String name) throws IOException {
        InputStream input = new FileInputStream(projectPath + "/src/main/java/Output/outputConfig.properties");
        prop.load(input);
        return prop.getProperty(name);
    }



}
