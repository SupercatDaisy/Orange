package com.orangehrm.utilities;

import io.cucumber.java.an.E;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    //same for everyone
    //Thread because our driver is singleton and parallel testing will try to use same object at the same time and fail
    private static ThreadLocal <WebDriver> driverPool = new ThreadLocal<>();
    //so noone can create object of class
    //everyone should call static getter method instead
    //thread local class creating instances for us for every thread.
    //created another instance when needed but in same thread its still one
    private Driver (){
    }

    //SYNC Enabbles multi-threading works little slower but make sure
    //two different method can access here at the same time
 /*   public synchronized static WebDriver getDriver(String browser) {
        if(driverPool.get()==null){
           // String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chromeheadless":
                    ChromeOptions chromeOptions=new ChromeOptions();
                    chromeOptions.setHeadless(true);
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                        break;
                case "InterExplorer":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if(!System.getProperty("os.name").toLowerCase().contains("mac")){
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":

                        ChromeOptions chromeOptions1=new ChromeOptions();
                        chromeOptions1.setCapability("platform", Platform.ANY);
                    try{
                        driverPool.set(new RemoteWebDriver(new URL("http://192.168.0.9:4444/wd/hub"),chromeOptions1));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":

                        FirefoxOptions firefoxOptions=new FirefoxOptions();
                        firefoxOptions.setCapability("platform", Platform.ANY);
                    try{
                        driverPool.set(new RemoteWebDriver(new URL("http://192.168.0.9:4444/wd/hub"),firefoxOptions));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }*/

   /*public synchronized static WebDriver getDriver() {
        if(driverPool.get()==null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chromeheadless":
                    ChromeOptions chromeOptions=new ChromeOptions();
                    chromeOptions.setHeadless(true);
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;

                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }*/
    public synchronized static WebDriver getDriver(String browser) {
        //if webdriver object doesn't exist
        //create it
        if (driverPool.get() == null) {
            //specify browser type in configuration.properties file
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")){
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        ChromeOptions remoteChromeOptions = new ChromeOptions();
                        remoteChromeOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL("http:http://192.168.0.9:4444/wd/hub"),remoteChromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":
                    try {
                        FirefoxOptions remoteFirefoxOptions = new FirefoxOptions();
                        remoteFirefoxOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL("http:34.207.104.148:4444/wd/hub"),remoteFirefoxOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver()
    {
        if(driverPool!=null)
        {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
// ThreadLocal allows to create a copy of the object at the run time for every thread.
//we need to make getter synchronized to prevent a crash so they will  not clash.
//it calls thread safety.
//to run data provider test in parallel add parameter (parallel =true)
// How to make parallel testing with WebDriver ? Interview question
//Make your webDriver object ThreadLocal and make your getter synchronized
