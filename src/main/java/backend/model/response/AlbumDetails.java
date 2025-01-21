package backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumDetails {
  private String album_id;
  private String title;
  private String artist;
  private String genre;
  private String label;
  private Object songs;
  private Integer year;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AlbumDetails that)) return false;
    return Objects.equals(title, that.title)
        && Objects.equals(artist, that.artist)
        && Objects.equals(genre, that.genre)
        && Objects.equals(label, that.label)
        && Objects.equals(songs, that.songs)
        && Objects.equals(year, that.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, artist, genre, label, songs, year);
  }
}
