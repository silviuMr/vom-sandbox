package frontEnd.pages.bankingApp;

import frontEnd.components.CustomersTableComponent;
import frontEnd.models.bankingApp.customers.CustomersTable;
import frontEnd.models.bankingApp.customers.CustomersTableLine;
import org.openqa.selenium.WebDriver;
import frontEnd.pages.BasePage;

public class CustomersListPage extends BasePage {
  private CustomersTableComponent customersTableComponent;

  public CustomersListPage(WebDriver webDriver) {
    super(webDriver);
  }

  public CustomersTable getCustomersTable() {
    customersTableComponent.isTableLoaded();
    return customersTableComponent.readCustomersTable();
  }

  public void deleteCustomerFromLine(CustomersTableLine customersTableLine) {
    customersTableLine.getDeleteCustomer().click();
  }
}
