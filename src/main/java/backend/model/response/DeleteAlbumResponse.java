package backend.model.response;

import lombok.Data;

@Data
public class DeleteAlbumResponse {
    private boolean acknowledged;
    private Integer deletedCount;
}
