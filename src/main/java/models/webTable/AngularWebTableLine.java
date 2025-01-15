package models.webTable;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Builder
@Data
public class AngularWebTableLine {
  private String firstName;
  private String lastName;
  private String userName;
  private String customer;
  private String role;
  private String email;
  private String cellPhone;
  private boolean locked;
  private WebElement editUserButton;
  private WebElement deleteUserButton;
}
