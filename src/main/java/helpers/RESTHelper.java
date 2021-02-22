package helpers;

import io.restassured.response.Response;

public class RESTHelper {

    public static <T> T settingResponseToObject(Response response, Class<T> responseClass) {
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Test exception: Status is incorrect, it was: " + response.getStatusCode());
        } else {
            return response.then().extract().as(responseClass);
        }
    }
}
