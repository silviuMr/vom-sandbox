package frontEnd.models.protractorApp.webTable;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
@Builder
public class AngularWebTableHeader {
  private WebElement searchBarElement;
  private WebElement addUserElement;
  private String firstNameHeader;
  private String lastNameHeader;
  private String usernameHeader;
  private String customerHeader;
  private String roleHeader;
  private String emailHeader;
  private String cellPhoneHeader;
  private String lockedHeader;
  private String editHeader;
  private String deleteHeader;
}
