package models.form;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
@Builder
public class EditUserForm {
  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private List<String> customerList;
  private String role;
  private String email;
  private boolean locked;

  private WebElement firstNameElement;
  private WebElement lastNameElement;
  private WebElement userNameElement;
  private WebElement passwordElement;
  private List<WebElement> customerListElement;
  private WebElement roleElement;
  private WebElement emailElement;
  private WebElement lockedElement;
  private WebElement saveButton;
  private WebElement closeButton;
}
