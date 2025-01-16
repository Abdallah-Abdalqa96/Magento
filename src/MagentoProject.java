import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoProject {
	
	WebDriver driver = new ChromeDriver();
	
	String myWebsite = "https://magento.softwaretestingboard.com/";

    @BeforeTest
    public void setUp() {
        
    	driver.get(myWebsite);
        driver.manage().window().maximize();
    }

    

    @Test(priority =1,enabled = false)
    public void testSignUp() {
        driver.findElement(By.linkText("Create an Account")).click();
        driver.findElement(By.id("firstname")).sendKeys("ahmad");
        driver.findElement(By.id("lastname")).sendKeys("Abdalqader");
        driver.findElement(By.id("email_address")).sendKeys("ahmad1995@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345OoOoO");
        driver.findElement(By.id("password-confirmation")).sendKeys("12345OoOoO");
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();
        assert driver.findElement(By.cssSelector(".message-success")).getText().contains("Thank you for registering");
    }

    @Test(priority = 2,enabled = true)
    public void testLogin() {
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("ahmad1995@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("12345OoOoO");
        driver.findElement(By.id("send2")).click();
        assert driver.findElement(By.cssSelector(".greet.welcome")).getText().contains("Welcome");
    }

    @Test(priority = 3,enabled =true )
    public void testAddItemsToCart() {
        driver.findElement(By.linkText("Gear")).click();
        driver.findElement(By.linkText("Bags")).click();
        List<WebElement> items = driver.findElements(By.cssSelector(".product-item"));
        for (int i = 0; i < items.size(); i++) {
            if (i % 2 == 0) {
                items.get(i).findElement(By.cssSelector(".action.tocart")).click();
            }
        }
        assert driver.findElements(By.cssSelector(".counter-number")).size() > 0;
    }

    @Test(priority = 4,enabled = true)
    public void testAssertion() {
        driver.findElement(By.linkText("Gear")).click();
        driver.findElement(By.linkText("Bags")).click();
        int totalItems = driver.findElements(By.cssSelector(".product-item")).size();
        for (int i = 0; i < totalItems; i++) {
            if (i % 2 == 0) {
                driver.findElements(By.cssSelector(".action.tocart")).get(i).click();
            }
        }
        int addedItemsCount = driver.findElements(By.cssSelector(".counter-number")).size();
        assert addedItemsCount == totalItems / 2;
    }

    @Test(priority = 5,enabled =true)
    public void testCheckout() {
        driver.findElement(By.linkText("Gear")).click();
        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.cssSelector(".action.tocart")).click();
        driver.findElement(By.cssSelector(".action.primary.checkout")).click();
        driver.findElement(By.id("shipping-firstname")).sendKeys("John");
        driver.findElement(By.id("shipping-lastname")).sendKeys("Doe");
        driver.findElement(By.id("shipping-address")).sendKeys("123 Main St");
        driver.findElement(By.id("shipping-city")).sendKeys("New York");
        driver.findElement(By.id("shipping-postcode")).sendKeys("10001");
        driver.findElement(By.id("shipping-telephone")).sendKeys("1234567890");
        driver.findElement(By.cssSelector(".radio.shipping-method")).click();
        driver.findElement(By.id("credit-card-number")).sendKeys("4111111111111111");
        driver.findElement(By.id("credit-card-expiration")).sendKeys("12/25");
        driver.findElement(By.id("credit-card-cvv")).sendKeys("123");
        driver.findElement(By.cssSelector(".action.primary.place-order")).click();
        assert driver.findElement(By.cssSelector(".page-title")).getText().contains("Thank you for your purchase");
    }
    //@AfterMethod
    //public void tearDown() {
     //   if (driver != null) {
       //     driver.quit();
       // }
    //}
}