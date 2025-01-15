import static enums.Url.*;
import static org.testng.Assert.*;

import enums.UserRoles;
import lombok.extern.slf4j.Slf4j;
import models.webTable.AngularWebTable;
import models.webTable.AngularWebTableLine;
import org.testng.TestException;
import org.testng.annotations.Test;
import pages.AngularWebTablePage;

@Slf4j
public class WebTablesTest extends BaseTest {

  @Test
  public void checkEditingUserDetails() {
    UserRoles desiredNewRole = UserRoles.SALES_TEAM;
    String desiredNewCompany = "Company AAA";

    log.info("Navigating to table homepage");
    driver.navigate().to(WEBTABLE_HOMEPAGE_URL.getUrl());
    AngularWebTablePage angularWebTablePage = new AngularWebTablePage(driver);
    assertTrue(angularWebTablePage.hasPageLoaded(), "Table page has not loaded successfully");

    log.info("Reading table");
    AngularWebTable angularWebTableInitialState = angularWebTablePage.readHomepageTable();

    //    log.info("Verify if the name exists in the table");
    //    assertTrue(
    //        angularWebTableInitialState.getTableLines().stream()
    //            .anyMatch(tl -> tl.getLastName().equals("Novak") &&
    // tl.getFirstName().equals("Mark")),
    //        "The name Mark Novak cannot be found in the table");

    log.info("Opening user details for Mark Novak");
    angularWebTableInitialState.getTableLines().stream()
        .filter(tl -> tl.getLastName().equals("Novak") && tl.getFirstName().equals("Mark"))
        .findFirst()
        .orElseThrow(() -> new TestException("The name Mark Novak cannot be found in the table"))
        .getEditUserButton()
        .click();
    assertTrue(angularWebTablePage.isEditUserDialogOpened(), "Edit user window is not present");

    //    log.info("Reading user details modal");
    //    EditUserForm editUserFormInitialState = angularWebTablePage.readEditUserForm();

    log.info("Change user role");
    angularWebTablePage.changeUserRole(desiredNewRole);
    angularWebTablePage.saveEditUserForm();

    log.info("Verify if the role was changed");
    AngularWebTable tableAfterRoleChange = angularWebTablePage.readHomepageTable();
    AngularWebTableLine changedLineWithNewRole =
        tableAfterRoleChange.getTableLines().stream()
            .filter(tl -> tl.getLastName().equals("Novak") && tl.getFirstName().equals("Mark"))
            .findFirst()
            .orElseThrow(
                () ->
                    new TestException(
                        "The name Mark Novak cannot be found in the table after the role change"));
    assertEquals(
        changedLineWithNewRole.getRole(),
        desiredNewRole.getRole(),
        "The role does not match the desired new role");

    log.info("Opening user details for test user");
    tableAfterRoleChange.getTableLines().stream()
        .filter(tl -> tl.getUserName().equals("test"))
        .findFirst()
        .orElseThrow(() -> new TestException("The name test cannot be found in the table"))
        .getEditUserButton()
        .click();
    assertTrue(angularWebTablePage.isEditUserDialogOpened(), "Edit user window is not present");

    log.info("Change user company");
    angularWebTablePage.changeUserCompany(desiredNewCompany);
    angularWebTablePage.saveEditUserForm();

    log.info("Verify if the company was changed");
    AngularWebTable tableAfterCompanyChange = angularWebTablePage.readHomepageTable();
    AngularWebTableLine changedLineWithNewCompany =
        tableAfterCompanyChange.getTableLines().stream()
            .filter(tl -> tl.getUserName().equals("test"))
            .findFirst()
            .orElseThrow(() -> new TestException("The name test cannot be found in the table"));

    log.info("Verify if the customer was changed");
    assertEquals(
        changedLineWithNewCompany.getCustomer(),
        desiredNewCompany,
        "The customer does not match the desired new company name");
  }
}
