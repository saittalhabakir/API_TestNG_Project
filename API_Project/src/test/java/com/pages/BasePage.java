package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {

    // To use page factory design pattern in POM
    public BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver.get(), 5), this);
    }

}
