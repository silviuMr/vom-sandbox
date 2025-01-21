package frontEnd.models.protractorApp.webTable;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AngularWebTable {
  private List<AngularWebTableLine> tableLines;
}
