package com.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtils {

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) throws IOException {
        // name the ss with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        //TakesScreenshot -> interface from selenium which takes screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs((OutputType.FILE));
        // full path for the ss location
        String target = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + name + date + ".png";
        File finalDestination = new File(target);
        //save the ss to the given path
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

}
