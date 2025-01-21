import static enums.Url.BANKING_HOMEPAGE_URL;
import static org.testng.Assert.*;

import frontEnd.models.bankingApp.customers.CustomersTable;
import frontEnd.models.bankingApp.customers.CustomersTableLine;
import frontEnd.pages.bankingApp.AddCustomerPage;
import frontEnd.pages.bankingApp.BankingHomePage;
import frontEnd.pages.bankingApp.CustomersListPage;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class BankingTest extends BaseTest {

  @Test
  public void verifyUserCreationAndDeletion() {
    final String expectedFirstName = "John";
    final String expectedLastName = "Wick";
    final String expectedPostCode = "888303";

    log.info("Navigating to the app");
    driver.navigate().to(BANKING_HOMEPAGE_URL.getUrl());
    BankingHomePage homePage = new BankingHomePage(driver);
    AddCustomerPage addCustomerPage = homePage.logInAsManager();

    log.info("Adding a new customer");
    String userId =
        addCustomerPage.addNewCustomerAndGetId(
            expectedFirstName, expectedLastName, expectedPostCode);
    CustomersListPage customersListPage = addCustomerPage.goToCustomersListPage();

    CustomersTable customersTable = customersListPage.readAndGetCustomersTable();
    CustomersTableLine newUserLine =
        customersTable.getCustomerTableLines().stream()
            .filter(l -> l.getFirstName().equals(expectedFirstName))
            .findAny()
            .orElseThrow(() -> new TestException("John Wick customer was not found in the table"));

    log.info("Asserting if the user details are correct");
    assertEquals(newUserLine.getFirstName(), expectedFirstName, "First name is not as expected");
    assertEquals(newUserLine.getLastName(), expectedLastName, "Last name is not as expected");
    assertEquals(newUserLine.getPostCode(), expectedPostCode, "Post code is not as expected");

    log.info("Deleting the added customer from the list");
    CustomersListPage customersListPageAfterDeletion =
        customersListPage.deleteCustomerFromLine(newUserLine);
    List<CustomersTableLine> customersTableAfterDeletion =
        customersListPageAfterDeletion.readAndGetCustomersTable().getCustomerTableLines();

    log.info("Checking if the user details are no longer present");
    assertTrue(
        customersTableAfterDeletion.stream()
            .noneMatch(l -> l.getFirstName().equals(expectedFirstName)),
        "First name is still in the table");
    assertTrue(
        customersTableAfterDeletion.stream()
            .noneMatch(l -> l.getLastName().equals(expectedLastName)),
        "Last name is still in the table");
    assertTrue(
        customersTableAfterDeletion.stream()
            .noneMatch(l -> l.getPostCode().equals(expectedPostCode)),
        "Post code is still in the table");
  }
}
