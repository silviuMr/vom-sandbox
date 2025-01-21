import backend.service.AlbumService;
import backend.service.PlaceholderService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

@Slf4j
public class BaseTest {
  public WebDriver driver = new ChromeDriver();
  AlbumService albumService = new AlbumService();
  PlaceholderService placeholderService = new PlaceholderService();

    @AfterClass
    public void quitDriver() {
      driver.quit();
    }
}
