package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RESTConnector {

    private static final String CONNECTION_URL =  "https://restful-booker.herokuapp.com/";
    private static final String USER = "admin";
    private static final String PASSWORD = "password123";

    public static Response GET(String endpoint) {
        return given()
                .headers(
                        "Content-Type", ContentType.JSON)
                .when().get(CONNECTION_URL + endpoint)
                .then().extract().response();
    }

    public static Response POST(String endpoint, String body) {
        return given()
                .headers(
                        "Content-Type", ContentType.JSON)
                .body(body)
                .when().post(CONNECTION_URL + endpoint)
                .then().extract().response();
    }

    public static Response DELETE(String endpoint) {
        return given().auth()
                .preemptive()
                .basic(USER, PASSWORD)
                .headers(
                        "Content-Type", ContentType.JSON)
                .when().delete(CONNECTION_URL + endpoint)
                .then().extract().response();
    }

    public static Response PUT(String endpoint, String body) {
        return PUT(endpoint, body, USER);
    }

    public static Response PUT(String endpoint, String body, String user) {
        return given()
                .auth()
                .preemptive()
                .basic(user, PASSWORD)
                .headers(
                        "Content-Type", ContentType.JSON)
                .body(body)
                .when().put(CONNECTION_URL + endpoint)
                .then().extract().response();
    }
}
