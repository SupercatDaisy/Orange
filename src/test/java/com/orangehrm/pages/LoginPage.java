package com.orangehrm.pages;

import com.orangehrm.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    @FindBy(id = "txtUsername")
    private WebElement username;

    @FindBy(id = "txtPassword")
    private WebElement password;

    @FindBy(id = "btnLogin")
    private WebElement Login;

    @FindBy(id = "spanMessage")
    private WebElement errorMsg;


    public void login(String username,String password){
        wait.until(ExpectedConditions.visibilityOf(this.username));
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Login));
        Login.click();
    }

    public void login(){
        wait.until(ExpectedConditions.visibilityOf(this.username));
        this.username.sendKeys(ConfigurationReader.getProperty("username"));
        this.password.sendKeys(ConfigurationReader.getProperty("password"));
        wait.until(ExpectedConditions.elementToBeClickable(Login));
        Login.click();
    }

    public String getErrorMsg(){
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg.getText();
    }

    public WebElement getErorStatus(){
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg;
    }







}
