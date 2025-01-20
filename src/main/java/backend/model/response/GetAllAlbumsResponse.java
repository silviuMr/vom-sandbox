package backend.model.response;

import lombok.Data;

@Data
public class GetAllAlbumsResponse {
  private AlbumDetails[] albumResponse;
}
