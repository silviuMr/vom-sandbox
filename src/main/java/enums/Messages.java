package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Messages {
    ALBUM_NOT_FOUND("The album with the given album_id was not found.");
    private final String message;
}
