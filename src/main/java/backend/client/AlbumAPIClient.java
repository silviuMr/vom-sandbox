package backend.client;

import static enums.Url.ALBUM_API_URL;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

public class AlbumAPIClient implements APIClient {
  @Override
  public Response get(String endpoint) {
    return given().baseUri(ALBUM_API_URL.getUrl())
            .accept(ContentType.JSON)
            .get(endpoint);
  }

  @Override
  public Response get(String endpoint, String query) {
    return given()
            .baseUri(ALBUM_API_URL.getUrl())
            .contentType(ContentType.ANY)
            .get(endpoint, query);
  }

  @Override
  public Response get(String endpoint, Map<String, String> queryParams) {
    return null;
  }

  @Override
  public Response post(String endpoint, String body) {
    return given().baseUri(ALBUM_API_URL.getUrl()).post(endpoint, body);
  }

  @Override
  public Response delete(String endpoint, String query) {
    return given().baseUri(ALBUM_API_URL.getUrl()).delete(endpoint, query);
  }
}
