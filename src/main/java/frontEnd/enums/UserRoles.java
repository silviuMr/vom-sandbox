package frontEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {
  SALES_TEAM("Sales Team"),
  CUSTOMER("Customer"),
  ADMIN("Admin");

  private final String role;
}
