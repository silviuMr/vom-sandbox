package frontEnd.components;

import static java.util.stream.Collectors.*;

import enums.UserRoles;
import java.util.List;
import frontEnd.models.protractorApp.form.EditUserForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestException;
import frontEnd.pages.BasePage;

public class EditUserComponent extends BasePage {
  @FindBy(xpath = "//input[@name='FirstName']")
  private WebElement firstName;

  @FindBy(xpath = "//input[@name='LastName']")
  private WebElement lastName;

  @FindBy(xpath = "//input[@name='UserName']")
  private WebElement username;

  @FindBy(xpath = "//input[@name='Password']")
  private WebElement password;

  @FindBy(xpath = "//input[@name='optionsRadios']")
  private List<WebElement> customerList;

  @FindBy(xpath = "//select[@name='RoleId']")
  private WebElement role;

  @FindBy(xpath = "//input[@name='Email']")
  private WebElement email;

  @FindBy(xpath = "//checkbox[@name='IsLocked']")
  private WebElement locked;

  @FindBy(xpath = "//button[text()='Close']")
  private WebElement closeButton;

  @FindBy(xpath = "//button[text()='Save']")
  private WebElement saveButton;

  @FindBy(xpath = "//form[@name='smartTableValidForm']")
  private WebElement formElement;

  public EditUserComponent(WebDriver webDriver) {
    super(webDriver);
  }

  public boolean isFormLoaded() {
    return formElement.isDisplayed();
  }

  public EditUserForm readEditUserForm() {
    return EditUserForm.builder()
        .firstName(firstName.getText())
        .lastName(lastName.getText())
        .userName(username.getText())
        .password(password.getText())
        .customerList(customerList.stream().map(WebElement::getText).collect(toList()))
        .role(role.getText())
        .email(email.getText())
        .locked(locked.isEnabled())
        .firstNameElement(firstName)
        .lastNameElement(lastName)
        .userNameElement(username)
        .passwordElement(password)
        .customerListElement(customerList)
        .roleElement(role)
        .emailElement(email)
        .lockedElement(locked)
        .closeButton(closeButton)
        .saveButton(saveButton)
        .build();
  }

  public void changeUserRoleTo(UserRoles roleName) {
    Select roleSelect = new Select(role);
    roleSelect.selectByValue(roleName.name());
  }

  public void changeUserCompanyTo(String companyName) {
    customerList.stream()
        .filter(cn -> cn.getText().equals(companyName))
        .findFirst()
        .orElseThrow(() -> new TestException("Company name was not found in the list"))
        .click();
  }

  public void closeForm() {
    closeButton.click();
  }

  public void saveForm() {
    saveButton.click();
  }
}
