package components;

import models.webTable.AngularWebTable;

public class HomepageTableComponent {
  public AngularWebTable readHomepageTable() {
    return AngularWebTable.builder()
            .tableHeader()
            .tableLines()
            .tableFooter()
            .build();
  }

}
