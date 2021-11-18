package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FeedPage;
import pages.HomePage;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;

public class DefinitionSteps {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    FeedPage feedPage;

    @Before
    public void setupClass() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User opens {string} page")
    public void userOpensHomePagePage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User clicks Russian language link")
    public void userClicksRussianLanguageLink() {
        homePage.clickRussianLanguageLink();
    }

    @Then("User checks the word {string} appears in the first paragraph")
    public void userChecksTheWordKeywordAppearsInTheFirstParagraph(final String keyword) {
        assertTrue(homePage.getFirstParagraphText().contains(keyword));
    }

    @When("User clicks Generate button")
    public void userClicksGenerateButton() {
        homePage.clickGenerateButton();
        feedPage = pageFactoryManager.getFeedPage();
    }

    @Then("User checks the first paragraph starts with {string}")
    public void userChecksTheFirstParagraphStartsWithText(final String text) {
        assertTrue(feedPage.getFirstParagraphText().startsWith(text));
    }

    @And("User clicks on {string}")
    public void userClicksOnType(final String type) {
        if ("words".equals(type)) {
            homePage.clickWordsRadio();
        } else if ("bytes".equals(type)) {
            homePage.clickBytesRadio();
        }
    }

    @And("User inputs {string} into the field")
    public void userInputsNumberIntoTheField(final String number) {
        homePage.clearInputField();
        homePage.sendKeysToInputField(number);
    }

    @Then("User checks the first paragraph has correct {string}")
    public void userChecksTheFirstParagraphHasCorrectSize(final String size) {
        String[] array = feedPage.getFirstParagraphText().split(" ");
        if (array.length == 1) {
            assertEquals(Integer.parseInt(size), array[0].length());
        } else {
            assertEquals(Integer.parseInt(size), array.length);
        }
    }

    @And("User clicks Start checkbox")
    public void userClicksStartCheckbox() {
        homePage.clickStartCheckbox();
    }

    @And("User checks the checkbox is not selected")
    public void userChecksTheCheckboxIsNotSelected() {
        assertFalse(homePage.isStartCheckboxSelected());
    }

    @Then("User checks the first paragraph does not start with {string}")
    public void userChecksTheFirstParagraphDoesNotStartWithText(final String text) {
        assertFalse(feedPage.getFirstParagraphText().startsWith(text));
    }

    @And("User gets text from each paragraph and determines the number of paragraphs containing {string}")
    public long userDeterminesTheNumberOfParagraphsContainingWord(final String text) {
        return feedPage.getListOfParagraphs().stream().filter(i -> i.getText().contains(text)).count();
    }

    @And("User runs the generation 10 times and gets the average number of paragraphs containing {string}")
    public int userGetsTheAverageNumberOfParagraphsContainingWord(final String text) {
        long count = 0;
        for (int i = 0; i < 10; i++) {
            driver.navigate().refresh();
            count += userDeterminesTheNumberOfParagraphsContainingWord(text);
        }
        return (int) count / 10;
    }

    @Then("User checks the value is not less than 2")
    public void userChecksTheValueIsNotLessThan2() {
        assertTrue(userGetsTheAverageNumberOfParagraphsContainingWord("lorem") >= 2);
    }
}
