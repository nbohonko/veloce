package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ContactsPage {
    public static final SelenideElement FORM_IFRAME = $(By.xpath("//iframe[contains(@src,'filesusr')]"));
    public static final By PAGE_TITLE = By.xpath("//h5[text()='Contact us']");
    public static final By INPUT_FIRST_NAME = By.id("first_name");
    public static final By INPUT_LAST_NAME = By.id("last_name");
    public static final By INPUT_EMAIL = By.id("email");
    public static final By INPUT_COMPANY = By.id("company");
    public static final By BUTTON_SUBMIT = By.name("submit");
    public static final By INPUT_CITY = By.id("city");
    public static final By INPUT_STATE = By.id("state");
    public static final By SUCCESS = By.xpath("//div[contains(@class, 'success')]");
}
