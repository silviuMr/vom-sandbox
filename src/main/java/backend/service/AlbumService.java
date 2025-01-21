package backend.service;

import static enums.Endpoints.*;
import static java.lang.String.format;

import backend.client.AlbumAPIClient;
import backend.model.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class AlbumService {
  AlbumAPIClient apiClient = new AlbumAPIClient();
  ObjectMapper mapper = new ObjectMapper();

  public AlbumDetails[] getAllAlbumsAsClass() {
    return apiClient.get(GET_POST_ALBUM).as(new TypeRef<>() {});
  }

  public AlbumDetails getAlbumByIdAsClass(String id) {
    return apiClient.get(format(GET_PUT_PATCH_SPECIFIC_ALBUM, id)).as(AlbumDetails.class);
  }

  public Response getAlbumById(String id) {
    return apiClient.get(format(GET_PUT_PATCH_SPECIFIC_ALBUM, id));
  }

  public PostAlbumResponse createNewAlbum(AlbumDetails albumDetails) {
    String bodyAsString = getStringFromBody(albumDetails);
    return apiClient.post(GET_POST_ALBUM, bodyAsString).as(PostAlbumResponse.class);
  }

  private String getStringFromBody(AlbumDetails albumDetails) {
    String bodyAsString;
    try {
      bodyAsString = mapper.writeValueAsString(albumDetails);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return bodyAsString;
  }

  public DeleteAlbumResponse deleteAlbum(String id) {
    return apiClient.delete(format(DELETE_ALBUM, id)).as(DeleteAlbumResponse.class);
  }

  public PatchAlbumResponse editAlbum(String id, AlbumDetails albumDetails) {
    String body = getStringFromBody(albumDetails);
    return apiClient
        .patch(format(GET_PUT_PATCH_SPECIFIC_ALBUM, id), body)
        .as(PatchAlbumResponse.class);
  }
}
