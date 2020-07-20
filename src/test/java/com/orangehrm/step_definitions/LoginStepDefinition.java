package com.orangehrm.step_definitions;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigurationReader;
import com.orangehrm.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinition {

    LoginPage loginPage = new LoginPage();

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        Driver.getDriver(ConfigurationReader.getProperty("browser")).get(ConfigurationReader.getProperty("url"));
    }

    @When("user enters correct username {string} and password {string} credentials")
    public void userEntersCorrectUsernameAndPasswordCredentials(String username, String password) {
        loginPage.login(username,password);
    }


    @Then("user should be able to login and verify {string} on welcome")
    public void userShouldBeAbleToLoginAndVerifyOnWelcome(String username) {
        Assert.assertTrue(loginPage.getWelcomeUser().contains(username));
    }


    @When("user enters wrong credentials as {string} and {string}")
    public void userEntersWrongCredentialsAsAnd(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("error message is displayed as {string}")
    public void errorMessageIsDisplayedAs(String errorMsg) {
        Assert.assertEquals(loginPage.getErrorMsg(), "Invalid credentials");
        // Assert.assertTrue(loginPage.getErorStatus().isDisplayed());
    }



}