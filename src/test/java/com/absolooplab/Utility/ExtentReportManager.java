package com.absolooplab.Utility;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportName;

    @Override
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        reportName = "Test-Report-" + timeStamp + ".html";

        ExtentSparkReporter spark =
                new ExtentSparkReporter("reports/" + reportName);

        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Functional Test Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("User", System.getProperty("user.name"));

        String browser =
                context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> groups =
                context.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	 Logger log = LogManager.getLogger(ExtentReportManager.class);
    	    log.error("TEST FAILED: {} | Reason: {}",
    	        result.getMethod().getMethodName(),
    	        result.getThrowable().getMessage());

        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.fail(result.getThrowable());

        String screenshotPath =
                ScreenshotUtil.capture(result.getMethod().getMethodName());

        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        try {
            File report = new File("reports/" + reportName);
            Desktop.getDesktop().browse(report.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}