package frontEnd.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  public WebDriver driver;
  protected int DEFAULT_WAIT_TIME = 5;

  public BasePage(WebDriver webDriver) {
    this.driver = webDriver;
    PageFactory.initElements(webDriver, this);
  }

  public WebDriverWait waitForElement(int seconds) {
    return new WebDriverWait(driver, Duration.ofSeconds(seconds));
  }

  protected WebElement waitUntilVisible(WebElement WebElement) {
    return waitForElement(DEFAULT_WAIT_TIME).until(visibilityOf(WebElement));
  }
}
