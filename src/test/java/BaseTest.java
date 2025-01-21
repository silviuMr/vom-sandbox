import backend.service.AlbumService;
import backend.service.PlaceholderService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

@Slf4j
public class BaseTest {
  WebDriver driver;
  Faker faker = new Faker();
  AlbumService albumService = new AlbumService();
  PlaceholderService placeholderService = new PlaceholderService();

  @AfterClass
  public void quitDriver() {
    if (driver != null) {
      driver.quit();
    }
  }
}
