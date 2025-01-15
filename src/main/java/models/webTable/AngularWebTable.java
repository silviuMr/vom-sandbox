package models.webTable;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AngularWebTable {
  private AngularWebTableHeader tableHeader;
  private List<AngularWebTableLine> tableLines;
  private AngularWebTableFooter tableFooter;
}
