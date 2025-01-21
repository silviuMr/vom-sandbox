import static enums.Messages.ALBUM_NOT_FOUND;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import backend.model.response.*;
import io.restassured.response.Response;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Slf4j
public class AlbumCollectionTest extends BaseTest {

  @Test
  public void verifyAlbumCreationAndDeletion() {
    log.info("Getting all existing albums");
    long totalNumberOfAlbums = getCurrentTotalNumberOfAlbums();

    log.info("Creating a new album");
    AlbumDetails newAlbum =
        AlbumDetails.builder()
            .title("New album for creation")
            .artist("New artist")
            .genre("New genre")
            .label("New label")
            .songs(159)
            .year(2025)
            .build();
    PostAlbumResponse createAlbumResponse = albumService.createNewAlbum(newAlbum);
    long totalNumberOfAlbumsAfterCreation = getCurrentTotalNumberOfAlbums();

    assertEquals(
        totalNumberOfAlbumsAfterCreation + 1,
        totalNumberOfAlbums,
        "The total number of albums has not increased");

    AlbumDetails albumDetails = albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(albumDetails, newAlbum, "The new album was created with different details");

    log.info("Deleting existing album");
    DeleteAlbumResponse deleteAlbumResponse =
        albumService.deleteAlbum(createAlbumResponse.getAlbum_id());
    assertTrue(deleteAlbumResponse.isAcknowledged(), "Album was not deleted");

    long totalNumberOfAlbumsAfterDeletion = getCurrentTotalNumberOfAlbums();
    assertEquals(
        totalNumberOfAlbumsAfterCreation - 1,
        totalNumberOfAlbumsAfterDeletion,
        "The total number of albums has not decreased");
  }

  private long getCurrentTotalNumberOfAlbums() {
    AlbumDetails[] allAlbumsResponseAfterCreation = albumService.getAllAlbumsAsClass();
    return Arrays.stream(allAlbumsResponseAfterCreation).count();
  }

  @Test
  public void verifyAlbumDetailsEditingAndDeletion() {
    final String desiredTitle = "Changed title";
    final int desiredSongs = 4;
    final int desiredYear = 1555;

    log.info("Creating a new album");
    AlbumDetails newAlbum =
        AlbumDetails.builder()
            .title("New album for editing")
            .artist("New artist")
            .genre("New genre")
            .label("New label")
            .songs(99)
            .year(1658)
            .build();
    PostAlbumResponse createAlbumResponse = albumService.createNewAlbum(newAlbum);
    long totalNumberOfAlbumsAfterCreation = getCurrentTotalNumberOfAlbums();

    log.info("Validate newly created album details");
    AlbumDetails albumDetails = albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(albumDetails, newAlbum, "The new album was created with different details");

    log.info("Changing album details");
    AlbumDetails editAlbumBody =
        AlbumDetails.builder().title(desiredTitle).songs(desiredSongs).year(desiredYear).build();
    PatchAlbumResponse patchAlbumResponse =
        albumService.editAlbum(createAlbumResponse.getAlbum_id(), editAlbumBody);
    assertEquals(patchAlbumResponse.getMatchedCount(), 1, "The album was not matched");
    assertEquals(patchAlbumResponse.getModifiedCount(), 1, "The album was not modified");

    log.info("Validate album details after title, songs and year editing");
    AlbumDetails albumDetailsAfterDetailsChanged =
        albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(
        albumDetailsAfterDetailsChanged,
        editAlbumBody,
        "The new album was created " + "with different details");

    log.info("Deleting genre and year from album");
    AlbumDetails deleteGenreYearBody = AlbumDetails.builder().genre(null).year(null).build();
    PatchAlbumResponse patchResponse =
        albumService.editAlbum(createAlbumResponse.getAlbum_id(), deleteGenreYearBody);
    assertEquals(patchResponse.getMatchedCount(), 1, "The album was not matched");
    assertEquals(patchResponse.getModifiedCount(), 1, "The album was not modified");

    log.info("Validate album details after genre and year deletion");
    AlbumDetails albumDetailsAfterGenreDeletion =
        albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(
        albumDetailsAfterGenreDeletion,
        deleteGenreYearBody,
        "The new album was " + "created with different details");

    log.info("Deleting created album");
    DeleteAlbumResponse deleteAlbumResponse =
        albumService.deleteAlbum(createAlbumResponse.getAlbum_id());
    assertTrue(deleteAlbumResponse.isAcknowledged(), "Album was not deleted");

    log.info("Validate album was deleted");
    Response detailsAfterDeletion = albumService.getAlbumById(createAlbumResponse.getAlbum_id());
    assertEquals(
        detailsAfterDeletion.getStatusCode(),
        HttpStatus.SC_NOT_FOUND,
        "Album was " + "not deleted");
    assertEquals(
        detailsAfterDeletion.getBody().prettyPrint(),
        ALBUM_NOT_FOUND.getMessage(),
        "Error message is not as expected");
  }
}
