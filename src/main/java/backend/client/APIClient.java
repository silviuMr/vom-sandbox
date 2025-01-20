package backend.client;

import io.restassured.response.Response;

import java.util.Map;

public interface APIClient {
  Response get(String endpoint);

  Response get(String endpoint, String query);

  Response get(String endpoint, Map<String, String> queryParams);

  Response post(String endpoint, String body);

  Response delete(String endpoint, String query);
}
