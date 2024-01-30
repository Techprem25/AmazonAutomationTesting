package com.amazon.base;

import com.amazon.PageObjectModel.ReusableMethods;
import com.amazon.testrail.TestRail;
import com.amazon.utils.LogUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import com.amazon.utils.PropertyUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseBrowser {
    protected static WebDriver driver;
    protected static Properties locatorProp;
    protected static int defaultWait;
    protected String testCaseId;
    public static ExtentReports extentReports;
    public static ExtentHtmlReporter htmlReport;
    public ExtentTest testCase;

    @BeforeMethod
    public static void launchBrowser() {

        BaseTest baseTest = new BaseTest();
        driver = baseTest.getBrowser(PropertyUtils.getBrowserName());
        extentReports = new ExtentReports();
        htmlReport = new ExtentHtmlReporter("Create_Test_Results/Test-Report.html");
        extentReports.attachReporter(htmlReport);
        LogUtils.printSteps("Chrome Driver setup is completed");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, SECONDS);
        driver.get(PropertyUtils.getUrl());
        LogUtils.printSteps("Browser launched successfully");
        locatorProp = new PropertyUtils().locatorProperties();
        defaultWait = Integer.parseInt(PropertyUtils.getWait());
    }

    @AfterMethod
    public void addResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            TestRail.addResultsForTestCase(testCaseId, TestRail.TEST_CASE_PASS_STATUS, "Test case passed successfully");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String filename = result.getMethod().getMethodName() + "__" + result.getThrowable().toString().split(":")[0].replaceAll("org.openqa.selenium", "") + ".png";
            ReusableMethods.captureScreen(driver, filename);
            try {
                testCase.addScreenCaptureFromPath(filename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TestRail.addResultsForTestCase(testCaseId, TestRail.TEST_CASE_FAIL_STATUS, "Test failed " + result.getThrowable().getMessage());
        }
        extentReports.flush();
        driver.quit();

    }
}
