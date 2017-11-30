import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class loginToLinguaLeoWithWrongPassword {


    private static WebDriver driver;

    @BeforeTest
    public static void SetUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://lingualeo.com/ru#welcome");
    }

    @Test
    public void userLoginWrongPass() {
        WebElement loginButton = driver.findElement(By.id("headEnterBtn"));
        loginButton.click();
        WebElement email = driver.findElement(By.xpath("//form[@id='loginForm']//child::*[@name='email']"));
        Actions builder = new Actions(driver);
        Actions seriesOfActions = builder.moveToElement(email).click().sendKeys(email, "marina@astra.od.ua");
        seriesOfActions.perform();
        WebElement pass = driver.findElement(By.xpath("//form[@id='loginForm']//child::*[@name='password']"));
        Actions series1fActions = builder.moveToElement(pass).click().sendKeys(pass, "qwe7777");
        series1fActions.perform();
        WebElement submit = driver.findElement(By.xpath("//*[@id='loginForm']/button"));
        submit.click();

        WebElement profileUser;
        profileUser = driver.findElement(By.cssSelector("p.uauth-email__error.t-ellps"));
        try {
           Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
       }
        //assertEquals(profileUser.getText(), "Пароль/email введены неверно");
        String mailUser = profileUser.getText();
        Assert.assertEquals(mailUser, "Пароль/email введены неверно");

    }
    @AfterTest
    public void tearDown() {

        driver.close();
    }
}


