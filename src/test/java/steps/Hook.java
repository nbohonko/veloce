package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.SoftAssert;

public class Hook {
    @Before
    public void setUp() {
        SoftAssert.clearSoftAssertionsErrorLog();
        Configuration.baseUrl = "https://www.veloceapps.com/";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        SoftAssert.summarizeAssertions();
    }
}
