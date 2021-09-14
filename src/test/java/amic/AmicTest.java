package amic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class AmicTest {

    private WebDriver driver;
    private HomePage homePage;
    private PayAndDeliveryPage payAndDeliveryPage;
    private AppleStorePage appleStorePage;
    private IphonePage iphonePage;
    private BasketPage basketPage;

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
        homePage = new HomePage(driver);
        payAndDeliveryPage = new PayAndDeliveryPage(driver);
        appleStorePage = new AppleStorePage(driver);
        iphonePage = new IphonePage(driver);
        basketPage = new BasketPage(driver);

    }

    @Test(priority = 1)
    public void checkThatSamsungPageContainsItems() {
        homePage.clickSumsungItems();
        assertEquals(homePage.getItemsCounter(), Integer.valueOf(698));
    }

    @Test(priority = 2)
    public void checkThatPageContainsUsefulLinks() {
        homePage.clickOnButtonPayAndDelivery();
        assertEquals(payAndDeliveryPage.safeCustomLinksInList(), Arrays.asList("Оплата і доставка",
                "Корпоративний відділ",
                "FAQ",
                "Контакти"));
    }

    @Test(priority = 3)
    public void checkWrongEmailError() {
        homePage.openSidebarItemWithProduct();
        homePage.openSidebarAplleStore();
        appleStorePage.clickOnIphonePage();
        iphonePage.clickOnButtonBuy();
        basketPage.clickOnButtonToOrder();
        basketPage.fillEmail();
        basketPage.fillName();
        basketPage.fillPhone();
        basketPage.clickOnButtonSubmit();
        basketPage.checkErrorMessage();
        assertEquals(basketPage.checkErrorMessage(), "Некорректный email");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
