package backend.service;

import static enums.Endpoints.*;

import backend.client.PlaceholderAPIClient;
import backend.model.request.PostNewPostRequest;
import backend.model.response.GetUsersRespone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class PlaceholderService {
  PlaceholderAPIClient placeholderAPIClient = new PlaceholderAPIClient();
  ObjectMapper mapper = new ObjectMapper();

  public GetUsersRespone[] getAllUsersAsClass() {
    return placeholderAPIClient.get(GET_USERS).as(new TypeRef<>() {});
  }

  public Response getAllUsers() {
    return placeholderAPIClient.get(GET_USERS);
  }

  public PostNewPostRequest postNewPostAsClass(PostNewPostRequest body) {
    String bodyAsString = getBodyAsString(body);
    return placeholderAPIClient
        .post(GET_POST_JSON_POSTS, bodyAsString)
        .as(PostNewPostRequest.class);
  }

  public Response postNewPost(PostNewPostRequest body) {
    String bodyAsString = getBodyAsString(body);
    return placeholderAPIClient.post(GET_POST_JSON_POSTS, bodyAsString);
  }

  private String getBodyAsString(Object body) {
    try {
      return mapper.writeValueAsString(body);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
