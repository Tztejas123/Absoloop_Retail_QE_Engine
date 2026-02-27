package com.absolooplab.Utility;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataUtil {

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    public static String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public static String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}