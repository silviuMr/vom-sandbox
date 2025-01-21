import static org.testng.Assert.*;

import backend.model.request.PostNewPostRequest;
import backend.model.response.GetUsersRespone;
import io.restassured.response.Response;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Slf4j
public class JsonPlaceholderTest extends BaseTest {

  @Test
  public void verifyRequestCreation() {
    final String userNameToSearch = "Karianne";
    final int desiredUserId = faker.number().numberBetween(1, 100);
    final String desiredTitle = faker.book().title();
    final String desiredBody = faker.book().author();

    log.info("Get all users");
    GetUsersRespone[] allUsers = placeholderService.getAllUsersAsClass();

    log.info("Check if requested user is present");
    assertTrue(
        Arrays.stream(allUsers).anyMatch(u -> u.getUsername().equals(userNameToSearch)),
        "Username " + userNameToSearch + " is not found in the list");

    log.info("Adding new post");
    PostNewPostRequest postNewPostRequest =
        PostNewPostRequest.builder()
            .userId(desiredUserId)
            .id(99)
            .body(desiredBody)
            .title(desiredTitle)
            .build();
    Response postResponse = placeholderService.postNewPost(postNewPostRequest);

    log.info("Check if the creation succeeded");
    assertEquals(postResponse.getStatusCode(), HttpStatus.SC_CREATED, "Post was not created");
  }

  @Test
  public void verifyRequestDuration() {
    final long desiredResponseTimeInMillis = 2000;

    log.info("Start counting duration");
    long startTime = System.currentTimeMillis();
    Response allUsers = placeholderService.getAllUsers();
    long endTime = System.currentTimeMillis();

    log.info("Check if the request was successful");
    assertEquals(allUsers.getStatusCode(), HttpStatus.SC_OK);

    log.info("Check if the response time was within limits");
    long time = endTime - startTime;
    assertTrue(
        time <= desiredResponseTimeInMillis,
        "Response time " + time + " was not within limits of " + desiredResponseTimeInMillis);
  }
}
