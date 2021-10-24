package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[contains(@href,'ru')]")
    private WebElement russianLanguageLink;
    @FindBy(xpath = "//h2[contains(text(), 'Что такое')]/../p")
    private WebElement definitionParagraph;
    @FindBy(xpath = "//input[@id='generate']")
    private WebElement generateButton;
    @FindBy(xpath = "//label[@for='words']")
    private WebElement wordsRadio;
    @FindBy(xpath = "//label[@for='bytes']")
    private WebElement bytesRadio;
    @FindBy(xpath = "//input[@id='amount']")
    private WebElement inputField;
    @FindBy(xpath = "//input[@id='start']")
    private WebElement startCheckbox;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickRussianLanguageLink() {
        russianLanguageLink.click();
    }

    public String getFirstParagraphText() {
        return definitionParagraph.getText();
    }

    public void clickGenerateButton() {
        generateButton.click();
    }

    public void clickWordsRadio() {
        wordsRadio.click();
    }

    public void clickBytesRadio() {
        bytesRadio.click();
    }

    public void clearInputField() {
        inputField.clear();
    }

    public void sendKeysToInputField(String number) {
        inputField.sendKeys(number);
    }

    public void clickStartCheckbox() {
        startCheckbox.click();
    }

    public boolean isStartCheckboxSelected() {
        return startCheckbox.isSelected();
    }
}
