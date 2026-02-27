package com.absolooplab.Utility;

import java.util.ResourceBundle;

public class ConfigManager {

    private static ResourceBundle rb =
            ResourceBundle.getBundle("config");

    public static String get(String key) {
        return rb.getString(key);
    }
}