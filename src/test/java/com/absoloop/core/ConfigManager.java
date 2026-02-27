package com.absoloop.core;

import java.util.ResourceBundle;

public class ConfigManager {

    private static final ResourceBundle rb = ResourceBundle.getBundle("config");

    private ConfigManager() {}

    public static String get(String key) {
        return rb.getString(key);
    }
}