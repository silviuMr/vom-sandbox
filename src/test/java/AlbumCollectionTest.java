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
    AlbumDetails newAlbum = createAlbumDetails();
    PostAlbumResponse createAlbumResponse = getPostAlbumResponse(newAlbum);
    long totalNumberOfAlbumsAfterCreation = getCurrentTotalNumberOfAlbums();

    assertTrue(
        totalNumberOfAlbums < totalNumberOfAlbumsAfterCreation,
        "The total number of albums has not increased");

    AlbumDetails albumDetails = albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(albumDetails, newAlbum, "The new album was created with different details");

    log.info("Deleting existing album");
    DeleteAlbumResponse deleteAlbumResponse =
        albumService.deleteAlbum(createAlbumResponse.getAlbum_id());
    assertTrue(deleteAlbumResponse.isAcknowledged(), "Album was not deleted");
    assertEquals(deleteAlbumResponse.getDeletedCount(), 1, "The album was not deleted");

    long totalNumberOfAlbumsAfterDeletion = getCurrentTotalNumberOfAlbums();
    assertTrue(
        totalNumberOfAlbumsAfterCreation > totalNumberOfAlbumsAfterDeletion,
        "The total number of albums has not decreased");
  }

  private long getCurrentTotalNumberOfAlbums() {
    AlbumDetails[] allAlbumsResponseAfterCreation = albumService.getAllAlbumsAsClass();
    return Arrays.stream(allAlbumsResponseAfterCreation).count();
  }

  @Test
  public void verifyAlbumDetailsEditingAndDeletion() {
    final String desiredTitle = " - Changed title";
    final int desiredSongs = 4;
    final int desiredYear = 1555;

    log.info("Creating a new album");
    AlbumDetails anotherAlbum = createAlbumDetails();
    PostAlbumResponse createAlbumResponse = albumService.createNewAlbum(anotherAlbum);

    log.info("Validate newly created album details");
    AlbumDetails albumDetails = albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(albumDetails, anotherAlbum, "The new album was created with different details");

    log.info("Changing album details");
    AlbumDetails editAlbumBody =
        AlbumDetails.builder()
            .title(albumDetails.getTitle() + desiredTitle)
            .songs(desiredSongs)
            .year(desiredYear)
            .build();
    PatchAlbumResponse patchAlbumResponse =
        albumService.editAlbum(createAlbumResponse.getAlbum_id(), editAlbumBody);
    assertEquals(patchAlbumResponse.getMatchedCount(), 1, "The album was not matched");

    // Slow updating - use wait library
    log.info("Validate album details after title, songs and year editing");
    AlbumDetails albumDetailsAfterDetailsChanged =
        albumService.getAlbumByIdAsClass(createAlbumResponse.getAlbum_id());
    assertEquals(
        albumDetailsAfterDetailsChanged.getTitle(),
        editAlbumBody.getTitle(),
        "The title was not updated");
    assertEquals(
        albumDetailsAfterDetailsChanged.getSongs(),
        editAlbumBody.getSongs(),
        "The songs were not updated");
    assertEquals(
        albumDetailsAfterDetailsChanged.getYear(),
        editAlbumBody.getYear(),
        "The year was not updated");

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

  private PostAlbumResponse getPostAlbumResponse(AlbumDetails albumDetails) {
    return albumService.createNewAlbum(albumDetails);
  }

  private AlbumDetails createAlbumDetails() {
    return AlbumDetails.builder()
        .title(faker.music().instrument())
        .artist(faker.artist().name())
        .genre(faker.music().genre())
        .label(faker.music().key())
        .songs(faker.number().numberBetween(1, 10))
        .year(faker.number().numberBetween(1955, 2025))
        .build();
  }
}
