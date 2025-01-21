package backend.client;

import io.restassured.response.Response;

public interface APIClient {
  Response get(String endpoint);

  Response post(String endpoint, String body);

  Response delete(String endpoint);

  Response patch(String endpoint, String body);
}
