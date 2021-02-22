package utils;


import org.assertj.core.api.SoftAssertions;

public class SoftAssert {
    private static SoftAssertions softAssertions = new SoftAssertions();

    public static void verifyTrue(Boolean condition, String elementName) {
        softAssertions.assertThat(condition).as("Incorrect state for " + elementName).isEqualTo(true);
    }

    public static void verifyFalse(Boolean condition, String elementName) {
        softAssertions.assertThat(condition).as("Incorrect state for " + elementName).isEqualTo(false);
    }

    public static void verifyEquals(String expected, String actual, String elementName) {
        softAssertions.assertThat(actual).as("Incorrect value for " + elementName).isEqualTo(expected);
    }

    public static void verifyEquals(Boolean expected, Boolean actual, String elementName) {
        softAssertions.assertThat(actual).as("Incorrect state for " + elementName).isEqualTo(expected);
    }

    public static void verifyEquals(int expected, int actual, String elementName) {
        softAssertions.assertThat(actual).as("Incorrect value for " + elementName).isEqualTo(expected);
    }

    public static void verifyContains(String expected, String target, String elementName) {
        softAssertions.assertThat(target).as("Invalid contains assertion for '" + elementName).contains(expected);
    }

    public static void fail(String failureMessage){
        softAssertions.fail(failureMessage);
    }

    public static void summarizeAssertions() {
        softAssertions.assertAll();
    }

    public static void clearSoftAssertionsErrorLog() {
        softAssertions = null;
        softAssertions = new SoftAssertions();
    }
}