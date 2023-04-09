package com.Tests;

import com.TestBase;
import com.pages.BuyProductPage;
import com.pages.CategoriesPage;
import com.pages.SignInPage;
import com.utilities.BrowserUtils;
import com.utilities.Driver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UI_Tests extends TestBase {

    @BeforeMethod
    public void setUp() {
        signInPage = new SignInPage();
        categoriesPage = new CategoriesPage();
        buyProductPage = new BuyProductPage();
    }

    @Test(priority = 1)
    public void signIn() {
        extentLogger = extent.createTest("User should successfully sign in");

        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";
        assertEquals(actualTitle, expectedTitle);
        extentLogger.info("verify that title is : " + expectedTitle + " user navigated correct web site");

        signInPage.loginPage();
        BrowserUtils.waitForPageToLoad(2);
        extentLogger.info("User enter registered email and password");

        String actualMessage = signInPage.myAccountText.getText();
        String expectedMessage = "Hesabım";
        assertEquals(actualMessage,expectedMessage);
        extentLogger.info("verify that user in : " + expectedMessage + "successfully");

        extentLogger.pass("Test is passed");
    }

    @Test(priority = 2)
    public void verifyNumeratedCategories() {

        extentLogger = extent.createTest("User should be able to check enumerated categories");

        signInPage.loginPage();
        BrowserUtils.waitForPageToLoad(2);

        extentLogger.info("Verify all the categories images are displayed successfully");
        try {
            for (int i = 0; i < categoriesPage.allTheCategoriesDiv.size(); i++) {
                categoriesPage.allTheCategoriesDiv.get(i).click();
                BrowserUtils.waitForVisibility(categoriesPage.boutiquesImage.get(0),5);
                for (int j = 0; j < categoriesPage.boutiquesImage.size(); j++) {
                    assertTrue(categoriesPage.boutiquesImage.get(j).isDisplayed());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        extentLogger.pass("Test is passed");
    }

    @Test(priority = 3)
    public void verifyRandomBoutiqueImages() {

        extentLogger = extent.createTest("User should check any boutiques images is displayed or not");

        signInPage.loginPage();
        BrowserUtils.waitForPageToLoad(1);

        extentLogger.info("Verify user choose random boutiques and check images are displayed successfully");
        try {
            categoriesPage.chooseAnyCategory();
            categoriesPage.chooseAnyBoutique();
            for (int i = 0; i < buyProductPage.boutiqueImagesList.size(); i++) {
                assertTrue(buyProductPage.boutiqueImagesList.get(i).isDisplayed());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        extentLogger.pass("Test is passed");
    }

    @Test(priority = 4)
    public void chooseAnyProductAndAddToMyCard() {
        extentLogger = extent.createTest("User should able to choose any product and add it to the card");

        signInPage.loginPage();
        BrowserUtils.waitForPageToLoad(2);

        extentLogger.info("User choose any product");
        categoriesPage.chooseAnyCategory();
        categoriesPage.chooseAnyBoutique();
        buyProductPage.chooseAnyProduct();
        extentLogger.info("User successfully add product to the card");
        buyProductPage.addToMyCart();

        BrowserUtils.waitForVisibility(buyProductPage.verifyAddedToBasketText,5);
        String actualMessage = buyProductPage.verifyAddedToBasketText.getText();
        String expectedMessage = "Sepete Eklendi";
        assertEquals(actualMessage,expectedMessage);
        extentLogger.info("Verify that user displayed " + expectedMessage + " text successfully");

        extentLogger.pass("Test is passed");
    }
}

