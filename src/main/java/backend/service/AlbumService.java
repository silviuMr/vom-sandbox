package backend.service;

import static enums.Endpoints.*;
import static java.lang.String.format;

import backend.client.AlbumAPIClient;
import backend.model.response.AlbumDetails;
import backend.model.response.DeleteAlbumResponse;
import backend.model.response.GetAllAlbumsResponse;
import backend.model.response.PostAlbumResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlbumService {
  AlbumAPIClient apiClient = new AlbumAPIClient();
  ObjectMapper mapper = new ObjectMapper();

  public GetAllAlbumsResponse getAllAlbums() {
    return apiClient.get(GET_POST_ALBUM).as(GetAllAlbumsResponse.class);
  }

  public AlbumDetails getAlbumById(String id) {
    return apiClient.get(format(GET_SPECIFIC_ALBUM, id)).as(AlbumDetails.class);
  }

  public PostAlbumResponse createNewAlbum(AlbumDetails albumDetails) {
    String bodyAsString;
    try {
      bodyAsString = mapper.writeValueAsString(albumDetails);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    //      albumDetails.
    return apiClient.post(GET_POST_ALBUM, bodyAsString).as(PostAlbumResponse.class);
  }

  public DeleteAlbumResponse deleteAlbum(String id) {
    return apiClient.delete(DELETE_ALBUM, id).as(DeleteAlbumResponse.class);
  }
}
