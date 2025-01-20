package frontEnd.pages.bankingApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import frontEnd.pages.BasePage;

public class AddCustomerPage extends BasePage {
  @FindBy(xpath = "//input[@ng-model='fName']")
  private WebElement firstNameInput;

  @FindBy(xpath = "//input[@ng-model='lName']")
  private WebElement lastNameInput;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement addCustomerButton;

  @FindBy(xpath = "//button[@ng-click='showCust()']")
  private WebElement customersButton;

  public AddCustomerPage(WebDriver webDriver) {
    super(webDriver);
  }

  public String getAlertMessage() {
    return driver.switchTo().alert().getText();
  }

  public void acceptAlert() {
    driver.switchTo().alert().accept();
  }

  public CustomersListPage goToCustomersPage() {
    customersButton.click();
    return new CustomersListPage(driver);
  }
}
