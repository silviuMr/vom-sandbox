package backend.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostNewPostRequest {
  private Integer userId;
  private Integer id;
  private String title;
  private String body;
}
