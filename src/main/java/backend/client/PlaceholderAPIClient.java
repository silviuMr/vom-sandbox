package backend.client;

import io.restassured.response.Response;

import java.util.Map;

public class PlaceholderAPIClient implements APIClient {
  @Override
  public Response get(String endpoint) {
    return null;
  }

  @Override
  public Response get(String endpoint, String query) {
    return null;
  }

  @Override
  public Response get(String endpoint, Map<String, String> queryParams) {
    return null;
  }

  @Override
  public Response post(String endpoint, String body) {
    return null;
  }

  @Override
  public Response delete(String endpoint, String query) {
    return null;
  }
}
