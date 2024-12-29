package api.utility;

import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import api.endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UtilityMethods {

    
    public void createOffer(int restaurantId, String offerType, int offerValue, String[] customerSegments) {
        JSONObject offerPayload = new JSONObject()
            .put("restaurant_id", restaurantId)
            .put("offer_type", offerType)
            .put("offer_value", offerValue)
            .put("customer_segment", new JSONArray(Arrays.asList(customerSegments)));

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(offerPayload.toString())
            .when()
            .post(Routes.Post_Add_Offer_Url)
            .then()
            .statusCode(200)
            .body("response_msg", equalTo("success"));
    }

   
    public Response applyOffer(int cartValue, int userId, int restaurantId) {
        JSONObject applyPayload = new JSONObject()
            .put("cart_value", cartValue)
            .put("user_id", userId)
            .put("restaurant_id", restaurantId);

        return RestAssured.given()
            .contentType(ContentType.JSON)
            .body(applyPayload.toString())
            .when()
            .post(Routes.Post_Apply_Offer_Url)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public Response createMockUser(int userId, String segment) {
        JSONObject mockRequestBody = new JSONObject()
            .put("httpRequest", new JSONObject()
                .put("method", "GET")
                .put("path", "/api/v1/user_segment")
                .put("queryStringParameters", new JSONObject()
                    .put("user_id", new JSONArray(Arrays.asList(String.valueOf(userId))))))
            .put("httpResponse", new JSONObject()
                .put("statusCode", 200)
                .put("body", new JSONObject()
                    .put("segment", segment).toString()));

        return RestAssured.given()
            .contentType(ContentType.JSON)
            .body(mockRequestBody.toString())
            .when()
            .put(Routes.Put_Mock_User_Url)
            .then()
            .statusCode(201) 
            .extract()
            .response();
    }
}
