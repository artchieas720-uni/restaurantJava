package models;

import java.io.*;
import java.util.Properties;

public class GameConfigReader {
    private final Properties properties = new Properties();

    public GameConfigReader(String path) {
        try (FileReader reader = new FileReader(path)) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("Error occur while loading " + e.getMessage());
        }
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key, double defaultValue) {
        return Double.parseDouble(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}