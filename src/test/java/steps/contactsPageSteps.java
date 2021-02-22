package steps;

import com.codeborne.selenide.ex.ElementNotFound;
import io.cucumber.java.en.And;
import pages.ContactsPage;
import utils.SoftAssert;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class contactsPageSteps {

    @And("^user switches to Contacts Frame$")
    public void userSwitchesToContactsFrame() throws InterruptedException {
        $(ContactsPage.FORM_IFRAME).shouldBe(visible);
        switchTo().frame(ContactsPage.FORM_IFRAME);
        try {
            $(ContactsPage.PAGE_TITLE).shouldBe(visible);
        } catch (ElementNotFound e) {
            Thread.sleep(1000);
            switchTo().frame(ContactsPage.FORM_IFRAME);
        }
    }

    @And("^user validates Contacts Frame elements$")
    public void userValidatesContactsFrameElements() {
        $(ContactsPage.PAGE_TITLE).shouldBe(visible);
        SoftAssert.verifyTrue($(ContactsPage.PAGE_TITLE).isDisplayed(), "PAGE_TITLE displayed");
        SoftAssert.verifyTrue($(ContactsPage.INPUT_FIRST_NAME).isDisplayed(), "INPUT_FIRST_NAME displayed");
        SoftAssert.verifyTrue($(ContactsPage.INPUT_LAST_NAME).isDisplayed(), "INPUT_LAST_NAME displayed");
        SoftAssert.verifyTrue($(ContactsPage.INPUT_EMAIL).isDisplayed(), "INPUT_EMAIL displayed");
        SoftAssert.verifyTrue($(ContactsPage.INPUT_COMPANY).isDisplayed(), "INPUT_COMPANY displayed");
        SoftAssert.verifyTrue($(ContactsPage.BUTTON_SUBMIT).isDisplayed(), "BUTTON_SUBMIT displayed");
    }

    @And("^user validates Contacts Second Frame elements$")
    public void userValidatesContactsSecondFrameElements() {
        userValidatesContactsFrameElements();
        SoftAssert.verifyTrue($(ContactsPage.INPUT_CITY).isDisplayed(), "INPUT_CITY displayed");
        SoftAssert.verifyTrue($(ContactsPage.INPUT_STATE).isDisplayed(), "INPUT_STATE displayed");
    }

    @And("^user validates Contacts Frame elements state$")
    public void userValidatesContactsPageElementsState(Map<String, String> fieldsToValidate) {
        for (Map.Entry<String, String> entry : fieldsToValidate.entrySet()) {
            Boolean expectedCondition = Boolean.parseBoolean(entry.getValue());
            switch (entry.getKey().toLowerCase()) {
                case "first name":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_FIRST_NAME).isEnabled(), "INPUT_FIRST_NAME enabled");
                    break;
                case "last name":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_LAST_NAME).isEnabled(), "INPUT_LAST_NAME enabled");
                    break;
                case "email":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_EMAIL).isEnabled(), "INPUT_EMAIL enabled");
                    break;
                case "company":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_COMPANY).isEnabled(), "INPUT_COMPANY enabled");
                    break;
                case "submit":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.BUTTON_SUBMIT).isEnabled(), "BUTTON_SUBMIT enabled");
                    break;
                case "city":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_CITY).isEnabled(), "INPUT_CITY enabled");
                    break;
                case "state":
                    SoftAssert.verifyEquals(expectedCondition,
                            $(ContactsPage.INPUT_STATE).isEnabled(), "INPUT_STATE enabled");
                    break;
                default:
                    throw new RuntimeException("Test exception - following field '" + entry.getKey() +
                            "' is not covered in any case");
            }
        }
    }

    @And("^user fills Contacts Frame elements$")
    public void userFillsContactsPageElements(Map<String, String> fieldsToFill) {
        for (Map.Entry<String, String> entry : fieldsToFill.entrySet()) {
            switch (entry.getKey().toLowerCase()) {
                case "first name":
                    $(ContactsPage.INPUT_FIRST_NAME).setValue(entry.getValue());
                    break;
                case "last name":
                    $(ContactsPage.INPUT_LAST_NAME).setValue(entry.getValue());
                    break;
                case "email":
                    $(ContactsPage.INPUT_EMAIL).setValue(entry.getValue());
                    break;
                case "company":
                    $(ContactsPage.INPUT_COMPANY).setValue(entry.getValue());
                    break;
                case "city":
                    $(ContactsPage.INPUT_CITY).setValue(entry.getValue());
                    break;
                case "state":
                    $(ContactsPage.INPUT_STATE).setValue(entry.getValue());
                    break;
                default:
                    throw new RuntimeException("Test exception - following field '" + entry.getKey() +
                            "' is not covered in any case");
            }
        }
    }

    @And("^user clicks on Submit button in Contacts Frame")
    public void userClicksOnSubmitButton() {
        $(ContactsPage.BUTTON_SUBMIT).click();
    }

    @And("^user does not see an error in Contact Frame$")
    public void userDoesNotSeeAnError() {
        $(ContactsPage.SUCCESS).shouldBe(visible);
    }
}
