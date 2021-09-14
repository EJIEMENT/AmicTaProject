package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PayAndDeliveryPage extends BasePage {

    @FindBy(xpath = "//a[@class='category-box-item']")
    private List<WebElement> customLinks;

    public PayAndDeliveryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> safeCustomLinksInList() {
        List<String> stringList = customLinks.stream().map(WebElement::getText).collect(Collectors.toList());
        return stringList;
    }

}
