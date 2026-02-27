package com.absolooplab.Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.absoloop.core.DriverManager;

public class ScreenshotUtil {

    public static String capture(String testName) {

        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            File source = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String targetPath = System.getProperty("user.dir")
                    + File.separator + "screenshots"
                    + File.separator + testName + "_" + timestamp + ".png";

            FileUtils.copyFile(source, new File(targetPath));

            return targetPath;

        } catch (Exception e) {
            throw new RuntimeException("Screenshot failed", e);
        }
    }
}