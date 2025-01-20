package frontEnd.models.bankingApp.customers;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
@Builder
public class CustomersTableLine {
  private String firstName;
  private String lastName;
  private String postCode;
  private String accountNumber;
  private WebElement deleteCustomer;
}
