package frontEnd.pages.protractorApp;

import frontEnd.components.EditUserComponent;
import frontEnd.components.HomepageTableComponent;
import frontEnd.enums.UserRoles;
import frontEnd.models.protractorApp.form.EditUserForm;
import frontEnd.models.protractorApp.webTable.AngularWebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import frontEnd.pages.BasePage;

public class AngularWebTablePage extends BasePage {
  private final EditUserComponent editUserComponent = new EditUserComponent(driver);
  private final HomepageTableComponent homepageTableComponent = new HomepageTableComponent(driver);

  @FindBy(xpath = "//table[@table-title='Smart Table example']")
  private WebElement tableElement;

  public AngularWebTablePage(WebDriver driver) {
    super(driver);
  }

  public boolean isEditUserDialogOpened() {
    return editUserComponent.isFormLoaded();
  }

  public boolean hasPageLoaded() {
    return tableElement.isDisplayed();
  }

  public AngularWebTable readHomepageTable() {
    return homepageTableComponent.readHomepageTable();
  }

  public EditUserForm readEditUserForm() {
    return editUserComponent.readEditUserForm();
  }

  public void saveEditUserForm() {
    editUserComponent.saveForm();
  }

  public void closeEditUserForm() {
    editUserComponent.closeForm();
  }

  public void changeUserRole(UserRoles userRole) {
    editUserComponent.changeUserRoleTo(userRole);
  }

  public void changeUserCompany(String companyName) {
    editUserComponent.changeUserCompanyTo(companyName);
  }
}
