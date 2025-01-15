package pages;

import components.EditUserComponent;
import components.HomepageTableComponent;
import enums.UserRoles;
import models.form.EditUserForm;
import models.webTable.AngularWebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AngularWebTablePage {
  private final EditUserComponent editUserComponent = new EditUserComponent();
  private final HomepageTableComponent homepageTableComponent = new HomepageTableComponent();

  public AngularWebTablePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//table[@table-title='Smart Table example']")
  private WebElement tableElement;

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
