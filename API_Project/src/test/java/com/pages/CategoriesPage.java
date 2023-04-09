package com.pages;

import com.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;


public class CategoriesPage extends BasePage {

    @FindBy(css=".tab-link")
    public List<WebElement> allTheCategoriesDiv;

    @FindBy(xpath="//span[@class ='image-container']/img")
    public List<WebElement> boutiquesImage;

    /**
     * User check all the categories which related to images. All the following method uses same purpose.
     *
     */

    public void chooseAnyCategory (){
        Random rd = new Random();
        int randomCategory = rd.nextInt(allTheCategoriesDiv.size()-1);
        allTheCategoriesDiv.get(randomCategory).click();
        System.out.println(allTheCategoriesDiv.get(randomCategory).getText()+ " category has been chosen");

    }

    public void chooseAnyBoutique (){
        Random rd = new Random();
        int randomBoutique = rd.nextInt(boutiquesImage.size());
        BrowserUtils.waitForClickability(boutiquesImage.get(randomBoutique),2).click();
    }

}

