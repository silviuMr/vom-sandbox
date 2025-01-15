package components;

import static java.util.stream.Collectors.*;

import java.util.List;
import models.form.EditUserForm;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserComponent {
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
}
