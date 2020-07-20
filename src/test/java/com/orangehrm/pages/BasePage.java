package com.orangehrm.pages;

import com.orangehrm.utilities.ConfigurationReader;
import com.orangehrm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {


    protected WebDriver driver = Driver.getDriver(ConfigurationReader.getProperty("browser"));
    protected WebDriverWait wait = new WebDriverWait(Driver.getDriver(ConfigurationReader.getProperty("browser")),10);

    public BasePage()
    {
        PageFactory.initElements(Driver.getDriver(ConfigurationReader.getProperty("browser")),this);
    }

    @FindBy(id = "welcome")
    private WebElement welcomeUser;


    public String getWelcomeUser()
    {
        wait.until(ExpectedConditions.visibilityOf(welcomeUser));
        return welcomeUser.getText();
    }

}
