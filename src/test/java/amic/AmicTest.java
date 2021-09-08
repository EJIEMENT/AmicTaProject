package amic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

public class AmicTest {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://avic.ua/ua");
    }

    @Test(priority = 1)
    public void checkThatSamsungPageContainsItems() {
        driver.findElement(xpath("//a[normalize-space()='']//img[@alt='Samsung']")).click();
        String actualProductsCountInCart =
                driver.findElement(xpath("//span[normalize-space()='(698)']"))
                        .getText();
        assertEquals(actualProductsCountInCart, "(698)");
    }

    @Test(priority = 2)
    public void checkThatPageContainsUsefulLinks() {
        driver.findElement(xpath("//a[@class='header-top__item'][contains(text(),'Оплата і доставка')]")).click();
        List<WebElement> elementList = driver.findElements(xpath("//a[@class='category-box-item']"));
        List<String> stringList = elementList.stream().map(WebElement::getText).collect(Collectors.toList());
        assertEquals(stringList, Arrays.asList("Оплата і доставка",
                "Корпоративний відділ",
                "FAQ",
                "Контакти"));
    }
    @Test(priority = 3)
    public void checkWrongEmailError(){
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//a[@class='prod-cart__buy'][contains(@data-ecomm-cart,'Slim Box White (MHDC3)')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Оформити замовлення')]")));
        driver.findElement(xpath("//a[contains(text(),'Оформити замовлення')]")).click();
        driver.findElement(xpath("//input[@name='email']")).sendKeys("123456789");
        driver.findElement(xpath("//input[@name='name']")).sendKeys("123456789");
        driver.findElement(xpath("//input[@name='phone']")).sendKeys("1234567890");
        driver.findElement(cssSelector("button[type='submit']")).click();
        String massage = driver.findElement(xpath("//div[@class='form-field input-field flex error']")).getAttribute("data-error");
        assertEquals(massage, "Некорректный email");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
