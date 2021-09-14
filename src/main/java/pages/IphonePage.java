package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IphonePage extends BasePage {
    @FindBy(xpath = "//div[@class = \"container-main\"]//div[@class = \"prod-cart height\"]//a[@class = \"prod-cart__buy\"][1]")
    private List<WebElement> buttonBuy;

    public IphonePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnButtonBuy() {
        new WebDriverWait(webDriver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        buttonBuy.stream().findFirst().get().click();
    }
}
