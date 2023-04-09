package com;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pages.CategoriesPage;
import com.pages.BuyProductPage;
import com.pages.SignInPage;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    protected static WebDriver driver;
    protected String url;
    public static ExtentReports extent;
    protected static ExtentSparkReporter spark;
    protected static ExtentTest extentLogger;
    protected CategoriesPage categoriesPage;
    protected BuyProductPage buyProductPage;
    protected SignInPage signInPage;

    @BeforeTest
    public void reportTest() {
        //create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";
        //initialize the html reporter with the report path
        spark = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        //attach the html report to report object
        extent.attachReporter(spark);
        //title in report
        spark.config().setReportName(ConfigurationReader.get("reportName"));
        ///set environment information
        extent.setSystemInfo("Trendyol", "UI Test");
        extent.setSystemInfo("Environment", "Staging");
        extent.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @BeforeMethod
    public void setUpTest() {
        url = ConfigurationReader.get("url");
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    // ITestResult class describes the result of a test in TestNG
    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        //If test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            //take the screenshot and return location of screenshot
            String screenShotPath = BrowserUtils.getScreenshot(result.getMethod().getMethodName());
            //add your screenshot to your report
            extentLogger.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());
        }
        //close driver
        Driver.closeDriver();
    }

    @AfterTest
    public void tearDownTest() {
        //this is when the report actually created
        extent.flush();
    }
}