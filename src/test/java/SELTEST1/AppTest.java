package SELTEST1;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class AppTest {

    public static   WebDriver driver;

    @BeforeClass
    public static void openBrowser (){
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver-v0.14.0-win64/geckodriver.exe");
         driver = new FirefoxDriver();
    }
    @Test
    public void  verifyLoadingLoginPage() {
        driver.navigate().to("http://qa.vrinfinity.com/");
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "http://qa.vrinfinity.com/login");
    }

    @Test
    public void verifyEmptyCredentialsLogin() {
        driver.navigate().to("http://qa.vrinfinity.com/");
        driver.findElement(By.className("login-form__btn-submit")).click();
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "http://qa.vrinfinity.com/login" );
        String userNamedRequired = driver.findElement(By.cssSelector("div.form-group:nth-child(3) > div:nth-child(3) > span:nth-child(1)")).getText();
        Assert.assertEquals(userNamedRequired, "Username is required.");
        String passwordRequired = driver.findElement(By.cssSelector("div.form-group:nth-child(4) > div:nth-child(3) > span:nth-child(1)")).getText();
        Assert.assertEquals(passwordRequired, "Password is required.");
    }

    @Test
    public void verifyEmptyPasswordLogin() {
        driver.navigate().to("http://qa.vrinfinity.com/");
        driver.findElement(By.name("userName")).sendKeys("Tanya");
        driver.findElement(By.className("login-form__btn-submit")).click();
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "http://qa.vrinfinity.com/login");
        String passwordRequired = driver.findElement(By.cssSelector("div.form-group:nth-child(4) > div:nth-child(3) > span:nth-child(1)")).getText();
        Assert.assertEquals(passwordRequired, "Password is required.");
    }

    @Test
    public void verifyEmptyUsernameLogin() {
        driver.navigate().to("http://qa.vrinfinity.com/");
        driver.findElement(By.name("password")).sendKeys("2098");
        driver.findElement(By.className("login-form__btn-submit")).click();
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "http://qa.vrinfinity.com/login" );
        String userNamedRequired = driver.findElement(By.cssSelector("div.form-group:nth-child(3) > div:nth-child(3) > span:nth-child(1)")).getText();
        Assert.assertEquals(userNamedRequired, "Username is required.");
    }

    @Test
    public void verifyInvalidUserNamePasswordLogin() throws InterruptedException {
        driver.navigate().to("http://qa.vrinfinity.com/");
        driver.findElement(By.name("userName")).sendKeys("Tanya");
        driver.findElement(By.name("password")).sendKeys("2015");
        driver.findElement(By.className("login-form__btn-submit")).click();
        Thread.sleep(5000);
        String Url = driver.getCurrentUrl();
        Assert.assertEquals(Url, "http://qa.vrinfinity.com/login" );
        String combination = driver.findElement(By.cssSelector("div.alert:nth-child(1)")).getText();
        Assert.assertEquals(combination, "Incorrect username/password combination");
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
