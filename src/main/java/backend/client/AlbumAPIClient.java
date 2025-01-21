package backend.client;

import static enums.Url.ALBUM_API_URL;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AlbumAPIClient implements APIClient {
  @Override
  public Response get(String endpoint) {
    return given()
        .baseUri(ALBUM_API_URL.getUrl())
        .basePath(endpoint)
        .accept(ContentType.JSON)
        .get();
  }

  @Override
  public Response post(String endpoint, String body) {
    return given()
        .baseUri(ALBUM_API_URL.getUrl())
        .basePath(endpoint)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(body)
        .post();
  }

  @Override
  public Response delete(String endpoint) {
    return given()
        .baseUri(ALBUM_API_URL.getUrl())
        .accept(ContentType.JSON)
        .basePath(endpoint)
        .delete();
  }

  @Override
  public Response patch(String endpoint, String body) {
    return given().baseUri(ALBUM_API_URL.getUrl()).basePath(endpoint).body(body).patch();
  }
}
