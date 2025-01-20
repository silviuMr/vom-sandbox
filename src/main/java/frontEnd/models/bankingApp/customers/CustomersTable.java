package frontEnd.models.bankingApp.customers;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomersTable {
  private List<CustomersTableLine> customerTableLines;
}
