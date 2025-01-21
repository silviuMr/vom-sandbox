package backend.model.response;

import lombok.Data;

@Data
public class PatchAlbumResponse {
    private boolean acknowledged;
    private Integer modifiedCount;
    private String[] upsertedId;
    private Integer modifiupsertedCountedCount;
    private Integer matchedCount;
}
