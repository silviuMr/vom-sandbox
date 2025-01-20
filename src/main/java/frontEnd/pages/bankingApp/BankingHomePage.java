package frontEnd.pages.bankingApp;

import frontEnd.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankingHomePage extends BasePage {
  @FindBy(xpath = "//button[@ng-click='manager()']")
  private WebElement managerLoginButton;

  public BankingHomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public AddCustomerPage logInAsManager() {
    managerLoginButton.click();
    return new AddCustomerPage(driver);
  }
}
