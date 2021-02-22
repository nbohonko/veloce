package steps;

import io.cucumber.java.en.And;
import utils.SoftAssert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class homePageSteps {

    @And("^user opens default URL$")
    public void userOpensDefaultUrl() {
        open("");
    }

    @And("^user closes Cookie bar$")
    public void userClosesCookieBar() {
        $(byText("Accept")).shouldBe(visible);
        $(byText("Accept")).click();
        $(byText("Accept")).shouldNotBe(visible);
    }

    @And("^user navigates to Contacts page from the Home page$")
    public void userNavigatesToPage() {
        $(byLinkText("COMPANY")).hover();
        if (!$(byLinkText("CONTACTS")).isDisplayed()) {
            $(byLinkText("COMPANY")).hover();
        }
        $(byLinkText("CONTACTS")).shouldBe(visible);
        $(byLinkText("CONTACTS")).click();
    }

    @And("^user summarizes assertions$")
    public void userSummarizesAssertions() {
        SoftAssert.summarizeAssertions();
    }
}
