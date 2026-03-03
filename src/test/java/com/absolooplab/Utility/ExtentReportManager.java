package com.absolooplab.Utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportName;

    @Override
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        reportName = "Test-Report-" + timeStamp + ".html";

        File reportDir = new File("reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

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
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().assignCategory(result.getMethod().getGroups());
        test.get().fail(result.getThrowable());

        // Extent Screenshot
        String screenshotPath =
                ScreenshotUtil.capture(result.getMethod().getMethodName());
        test.get().addScreenCaptureFromPath(screenshotPath);

        // Allure Screenshot
        AllureUtil.attachScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        try {

            // =======================
            // 1️⃣ Open Extent Report
            // =======================
            File extentReport = new File("reports/" + reportName);
            if (extentReport.exists()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            }

            // =======================
            // 2️⃣ Generate Allure Report
            // =======================
            ProcessBuilder builder = new ProcessBuilder(
                    "C:\\Program Files\\allure-2.33.0\\bin\\allure.bat",
                    "serve",
                    "allure-results"
            );

            builder.inheritIO();
            builder.start(); 

            // =======================
            // 3️⃣ Open Allure Report
            // =======================
            File allureReport =
                    new File("target/allure-report/index.html");

            if (allureReport.exists()) {
                Desktop.getDesktop().browse(allureReport.toURI());
            }

        } catch (Exception e) {
            System.out.println("Unable to open reports automatically.");
            e.printStackTrace();
        }
    }
}