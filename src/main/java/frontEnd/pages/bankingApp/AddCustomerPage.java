package frontEnd.pages.bankingApp;

import frontEnd.pages.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomerPage extends BasePage {
  @FindBy(xpath = "//input[@ng-model='fName']")
  private WebElement firstNameInput;

  @FindBy(xpath = "//input[@ng-model='lName']")
  private WebElement lastNameInput;

  @FindBy(xpath = "//input[@ng-model='postCd']")
  private WebElement postCodeInput;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement addNewCustomerButton;

  @FindBy(xpath = "//button[@ng-click='addCust()']")
  private WebElement addNewCustomerHeaderButton;

  @FindBy(xpath = "//button[@ng-click='showCust()']")
  private WebElement customersButton;

  public AddCustomerPage(WebDriver webDriver) {
    super(webDriver);
  }

  public AddCustomerPage goToAddCustomerPage() {
    addNewCustomerHeaderButton.click();
    return this;
  }

  public String getAlertMessage() {
    return driver.switchTo().alert().getText();
  }

  public void acceptAlert() {
    driver.switchTo().alert().accept();
  }

  public CustomersListPage goToCustomersListPage() {
    customersButton.click();
    return new CustomersListPage(driver);
  }

  public String addNewCustomerAndGetId(String firstName, String lastName, String postCode) {
    firstNameInput.sendKeys(firstName);
    lastNameInput.sendKeys(lastName);
    postCodeInput.sendKeys(postCode);
    addNewCustomerButton.click();

    String alertMessage = getAlertMessage();
    acceptAlert();
    return StringUtils.substringAfterLast(alertMessage, "id :");
  }
}
