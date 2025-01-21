package backend.client;

import static enums.Url.*;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PlaceholderAPIClient implements APIClient {
  @Override
  public Response get(String endpoint) {
    return given().baseUri(JSON_API_URL.getUrl()).basePath(endpoint).accept(ContentType.JSON).get();
  }

  @Override
  public Response post(String endpoint, String body) {
    return given()
        .baseUri(JSON_API_URL.getUrl())
        .basePath(endpoint)
        .body(body)
        .accept(ContentType.JSON)
        .post();
  }

  @Override
  public Response delete(String endpoint) {
    return null;
  }

  @Override
  public Response patch(String endpoint, String body) {
    return null;
  }
}
