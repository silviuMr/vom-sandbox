import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import backend.model.response.AlbumDetails;
import backend.model.response.DeleteAlbumResponse;
import backend.model.response.GetAllAlbumsResponse;
import backend.model.response.PostAlbumResponse;
import java.util.Arrays;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
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
            .title("New album")
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

    AlbumDetails albumDetails = albumService.getAlbumById(createAlbumResponse.getAlbum_id());
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
    GetAllAlbumsResponse allAlbumsResponseAfterCreation = albumService.getAllAlbums();
    return Arrays.stream(allAlbumsResponseAfterCreation.getAlbumResponse()).count();
  }
}
