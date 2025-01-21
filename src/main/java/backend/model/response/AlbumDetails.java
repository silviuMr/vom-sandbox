package backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDetails {
  private String album_id;
  private String title;
  private String artist;
  private String genre;
  private String label;
  //custom deserializer???
  private Integer songs;
  private Integer year;
}
