package com.pages;

import com.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//p[contains(text(),'Hesabım')]")
    public WebElement myAccountText;

    @FindBy(id = "login-email")
    public WebElement emailInput;

    @FindBy(id = "login-password-input")
    public WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    public WebElement submitBtn;


    /**
     * User enter registered email and password for sign in
     *
     */

    public void loginPage() {

        String userEmail = ConfigurationReader.get("userEmail");
        String userPassword = ConfigurationReader.get("userPassword");
        emailInput.sendKeys(userEmail);
        passwordInput.sendKeys(userPassword);
        submitBtn.click();
    }


}
