package com.pages;

import com.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Random;

public class BuyProductPage extends BasePage {

    @FindAll({
            @FindBy(how = How.XPATH, using = "//div[@class ='image-container']"),
            @FindBy(how = How.XPATH, using = "//div[@class ='image-container short-image-container']")
    })
    public List<WebElement> productList;

    @FindBy(xpath = "//div[@class ='image-container']")
    public List<WebElement> boutiqueImagesList;

    @FindBy(css=".add-to-basket")
    public WebElement addToBasketBtn;

    @FindBy(xpath = "//div[contains(text(),'Sepete Eklendi')]")
    public WebElement verifyAddedToBasketText;

    /**
     * This method uses choose any product
     */
    public void chooseAnyProduct (){
        Random rd = new Random();
        int randomProduct = rd.nextInt(productList.size());
        BrowserUtils.waitForClickability(productList.get(randomProduct),5).click();
    }

    /**
     * This method uses added to product to basket
     */
    public void addToMyCart(){
        BrowserUtils.waitForClickability(addToBasketBtn,5).click();
    }

}
