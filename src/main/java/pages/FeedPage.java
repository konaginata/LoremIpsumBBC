package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeedPage extends BasePage {
    @FindBy(xpath = "//div[@id='lipsum']/p[1]")
    private WebElement firstParagraph;

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstParagraphText() {
        return firstParagraph.getText();
    }
}
