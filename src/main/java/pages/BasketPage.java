package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'Оформити замовлення')]")
    public WebElement buttonToOrder;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneField;
    @FindBy(css = "button[type='submit']")
    public WebElement buttonSubmit;
    @FindBy(xpath = "//div[@class='form-field input-field flex error']")
    public WebElement errorMessage;

    public BasketPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnButtonToOrder() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Оформити замовлення')]")));
        buttonToOrder.click();
    }

    public void fillEmail() {
        emailField.sendKeys("123456789");
    }

    public void fillName() {
        nameField.sendKeys("123456789");
    }

    public void fillPhone() {
        phoneField.sendKeys("1234567890");
    }

    public void clickOnButtonSubmit() {
        buttonSubmit.click();
    }

    public String checkErrorMessage() {
        return errorMessage.getAttribute("data-error");
    }
}
