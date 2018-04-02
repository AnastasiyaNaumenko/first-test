package ua.org.autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ya.ru");
    }

    @Test
    public void userLogin() throws InterruptedException {

        Thread.sleep(3000);
        WebElement tabLogin = driver.findElement(By.cssSelector("a.link_black_novisit"));
        Assert.assertTrue(tabLogin.isEnabled());
        WebElement clickTabLogin = driver.findElement(By.cssSelector("a.link_black_novisit"));
        clickTabLogin.click();

        WebElement loginField = driver.findElement(By.cssSelector("a.button2.button2_size_mail-big.button2_theme_mail-white.button2_type_link.HeadBanner-Button.HeadBanner-Button-Enter.with-shadow"));
        Assert.assertTrue(loginField.isEnabled());
        loginField.click();

        WebElement loginInput = driver.findElement(By.name("login"));
        loginInput.sendKeys("autotestorgua");

        WebElement passwordInput = driver.findElement(By.name("passwd"));
        Assert.assertTrue(passwordInput.isDisplayed());
        passwordInput.sendKeys("autotestorgua123");

        WebElement loginButton = driver.findElement(By.xpath(".//*[text()='Войти']/.."));
        Assert.assertTrue(loginButton.isEnabled());
        loginButton.click();

        WebElement profileUser = driver.findElement(By.cssSelector("div.mail-User-Name"));
        Assert.assertTrue(profileUser.isDisplayed());
        String mailUser = profileUser.getText();
        Assert.assertEquals("autotestorgua", mailUser);
    }

    @AfterClass
    public static void tearDown() {
        WebElement menuUser = driver.findElement(By.cssSelector("span.mail-User-Avatar.mail-User-Avatar_size_42.mail-User-Avatar_header.js-user-picture"));
        Assert.assertTrue(menuUser.isEnabled());
        WebElement clickMenuUser = driver.findElement(By.cssSelector("span.mail-User-Avatar.mail-User-Avatar_size_42.mail-User-Avatar_header.js-user-picture"));
        clickMenuUser.click();

        WebElement logoutButton = driver.findElement(By.xpath(".//*[text()='Выйти из сервисов Яндекса']/.."));
        Assert.assertTrue(logoutButton.isEnabled());
        logoutButton.click();
        driver.quit();
    }
}