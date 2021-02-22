package steps;

import helpers.InputDataParser;
import helpers.RESTHelper;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import restObjects.Booking;
import utils.RESTConnector;
import utils.SoftAssert;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.BaseStep.getData;
import static utils.BaseStep.saveData;

public class restSteps {

    @Given("^user sends GET request to ([^\"]*) ([^\"]*) and receives 200 status code$")
    public void userSendsGetRequestAndReceives200StatusCode(String apiRequest, String bookingId) {
        Response response = RESTConnector.GET(apiRequest + InputDataParser.getParsedData(bookingId));
        if (apiRequest.contains("booking")) {
            Booking responseObject = RESTHelper.settingResponseToObject(response, Booking.class);
            saveData("bookingObject", responseObject);
        }
        saveData("latestResponse", response.getBody().asString());
    }

    @Given("^user sends GET request to ([^\"]*) ([^\"]*) and receives 404 status code$")
    public void userSendsGetRequestAndReceives404StatusCode(String apiRequest, String bookingId) {
        Response response = RESTConnector.GET(apiRequest + InputDataParser.getParsedData(bookingId));
        SoftAssert.verifyEquals(404, response.getStatusCode(), "Status code for GET. Response: " + getData("latestResponse"));
        SoftAssert.verifyEquals("Not Found", response.asString(), "Response message for GET. Response: " + response);
    }

    @Given("^user sends static POST request to ([^\"]*) and receives 200 status code$")
    public void userSendsStaticPostRequestAndReceives200StatusCode(String apiRequest, String jsonBody) {
        Response response = RESTConnector.POST(apiRequest, jsonBody);
        SoftAssert.verifyEquals(200, response.getStatusCode(), "Status code for POST. Response: " + response);
        if (apiRequest.equals("booking")) {
            Pattern pattern = Pattern.compile("\"bookingid\":(\\d+)");
            Matcher matcher = pattern.matcher(response.asString());
            if (matcher.find()) {
                saveData("savedBookingId", matcher.group(1));
            } else {
                SoftAssert.fail("No bookingid found in the response: " + response.asString());
            }
        }
    }

    @Given("^user sends static PUT request to ([^\"]*) ([^\"]*) and receives 200 status code$")
    public void userSendsStaticPutRequestAndReceives200StatusCode(String apiRequest, String bookingId, String jsonBody) {
        Response response = RESTConnector.PUT(apiRequest + InputDataParser.getParsedData(bookingId), jsonBody);
        SoftAssert.verifyEquals(200, response.getStatusCode(), "Status code for PUT. Response: " + response);
    }

    @Given("^user sends static PUT request to ([^\"]*) ([^\"]*) with incorrect user and receives 403 status code$")
    public void userSendsStaticPutRequestWithIncorrectUserAndReceives403StatusCode(String apiRequest, String bookingId, String jsonBody) {
        Response response = RESTConnector.PUT(apiRequest + InputDataParser.getParsedData(bookingId), jsonBody, "user");
        SoftAssert.verifyEquals(403, response.getStatusCode(), "Status code for PUT. Response: " + response);
        SoftAssert.verifyEquals("Forbidden", response.asString(), "Response message for PUT. Response: " + response);
    }

    @Given("^user sends DELETE request to ([^\"]*) ([^\"]*) and receives 201 status code$")
    public void userSendsDeleteRequestAndReceives200StatusCode(String apiRequest, String bookingId) {
        Response response = RESTConnector.DELETE(apiRequest + InputDataParser.getParsedData(bookingId));
        SoftAssert.verifyEquals(201, response.getStatusCode(), "Status code for DELETE. Response: " + response);
        SoftAssert.verifyEquals("Created", response.asString(), "Response message for DELETE. Response: " + response);
    }

    @Given("^user validates response for GET /booking$")
    public void userValidatesResponseForAgreementDetails(Map<String, String> inputData) {
        Booking bookingObject = (Booking) getData("bookingObject");
        String latestResponse = getData("latestResponse").toString();
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            switch (entry.getKey()) {
                case "firstname":
                    SoftAssert.verifyEquals(entry.getValue(), bookingObject.firstname,
                            "firstname. Response: " + latestResponse);
                    break;
                case "lastname":
                    SoftAssert.verifyEquals(entry.getValue(), bookingObject.lastname,
                            "lastname. Response: " + latestResponse);
                    break;
                case "totalprice":
                    SoftAssert.verifyEquals(entry.getValue(), Integer.toString(bookingObject.totalprice),
                            "totalprice. Response: " + latestResponse);
                    break;
                case "depositpaid":
                    SoftAssert.verifyEquals(entry.getValue(), Boolean.toString(bookingObject.depositpaid),
                            "depositpaid. Response: " + latestResponse);
                    break;
                case "checkin":
                    SoftAssert.verifyEquals(entry.getValue(), bookingObject.bookingdates.checkin,
                            "checkin. Response: " + latestResponse);
                    break;
                case "checkout":
                    SoftAssert.verifyEquals(entry.getValue(), bookingObject.bookingdates.checkout,
                            "checkout. Response: " + latestResponse);
                    break;
                case "additionalneeds":
                    SoftAssert.verifyEquals(entry.getValue(), bookingObject.additionalneeds,
                            "additionalneeds. Response: " + latestResponse);
                    break;
                default:
                    SoftAssert.fail("Field '" + entry.getKey() + "' is not present in the response" + latestResponse);
            }
        }
    }
}
