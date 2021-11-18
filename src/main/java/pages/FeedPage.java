package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeedPage extends BasePage {
    @FindBy(xpath = "//div[@id='lipsum']/p[1]")
    private WebElement firstParagraph;

    @FindBy(xpath = "//div[@id='lipsum']/p")
    private List<WebElement> allParagraphs;

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstParagraphText() {
        return firstParagraph.getText();
    }

    public List<WebElement> getListOfParagraphs() {
        return allParagraphs;
    }
}
