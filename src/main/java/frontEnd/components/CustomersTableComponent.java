package frontEnd.components;

import static java.lang.String.*;
import static org.openqa.selenium.By.xpath;

import java.util.ArrayList;
import java.util.List;
import frontEnd.models.bankingApp.customers.CustomersTable;
import frontEnd.models.bankingApp.customers.CustomersTableLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import frontEnd.pages.BasePage;

public class CustomersTableComponent extends BasePage {
  @FindBy(xpath = "//table/tbody")
  private WebElement tableBody;

  @FindBy(xpath = "//tbody/tr")
  private List<WebElement> tableLine;

  public CustomersTableComponent(WebDriver webDriver) {
    super(webDriver);
  }

  public boolean isTableLoaded() {
    return waitUntilVisible(tableBody).isDisplayed();
  }

  public CustomersTable readCustomersTable() {
    List<CustomersTableLine> customersTableLineList = new ArrayList<>();

    waitUntilVisible(tableBody);
    String commonSelector = "//tbody/tr[%d]/td[%d]";
    String buttonSelector = "//tbody/tr[%d]/td[5]//button[text()='Delete']";

    for (int i = 0; i < tableLine.size(); i++) {
      WebElement firstName = driver.findElement(xpath(format(commonSelector, i+1, 1)));
      WebElement lastName = driver.findElement(xpath(format(commonSelector, i+1, 2)));
      WebElement postCode = driver.findElement(xpath(format(commonSelector, i+1, 3)));
      WebElement deleteButton = driver.findElement(xpath(format(buttonSelector, i+1)));

      customersTableLineList.add(
          CustomersTableLine.builder()
              .firstName(firstName.getText())
              .lastName(lastName.getText())
              .postCode(postCode.getText())
              .deleteCustomer(deleteButton)
              .build());
    }

    return CustomersTable.builder().customerTableLines(customersTableLineList).build();
  }
}
