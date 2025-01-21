package frontEnd.pages.bankingApp;

import frontEnd.components.CustomersTableComponent;
import frontEnd.models.bankingApp.customers.CustomersTable;
import frontEnd.models.bankingApp.customers.CustomersTableLine;
import frontEnd.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class CustomersListPage extends BasePage {
  private final CustomersTableComponent customersTableComponent;

  public CustomersListPage(WebDriver webDriver) {
    super(webDriver);
    customersTableComponent = new CustomersTableComponent(driver);
  }

  public CustomersTable readAndGetCustomersTable() {
    customersTableComponent.isTableLoaded();
    return customersTableComponent.readCustomersTable();
  }

  public CustomersListPage deleteCustomerFromLine(CustomersTableLine customersTableLine) {
    customersTableLine.getDeleteCustomer().click();
    return this;
  }
}
