package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AppleStorePage extends BasePage {
    @FindBy(xpath = "//div[@class='brand-box__title']/a[contains(@href,'iphone')]")
    private WebElement iphonePageButton;

    public AppleStorePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnIphonePage() {
        iphonePageButton.click();

    }
}
