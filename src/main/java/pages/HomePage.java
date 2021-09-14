package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[normalize-space()='']//img[@alt='Samsung']")
    private WebElement samsungItems;
    @FindBy(xpath = "//a[@class='header-top__item'][contains(text(),'Оплата і доставка')]")
    private WebElement tagPayAndDelivery;
    @FindBy(xpath = "//span[normalize-space()='(698)']")
    private WebElement itemCount;
    @FindBy(xpath = "//span[@class='sidebar-item']")
    private WebElement sidebarItem;
    @FindBy(xpath = "//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")
    private WebElement sidebarAplleStore;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickSumsungItems() {
        samsungItems.click();
    }

    public void clickOnButtonPayAndDelivery() {
        tagPayAndDelivery.click();
    }

    public Integer getItemsCounter() {
        return Integer.parseInt(itemCount.getText().substring(1, itemCount.getText().length() - 1));
    }

    public void openSidebarItemWithProduct() {
        sidebarItem.click();
    }

    public void openSidebarAplleStore() {
        sidebarAplleStore.click();
    }


}
