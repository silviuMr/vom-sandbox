package components;

import java.util.List;
import models.webTable.AngularWebTable;
import models.webTable.AngularWebTableFooter;
import models.webTable.AngularWebTableHeader;
import models.webTable.AngularWebTableLine;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomepageTableComponent {
  private AngularWebTableHeader header;
  private AngularWebTableFooter footer;
  private List<AngularWebTableLine> tableLines;

  @FindBy(xpath = "//thead/tr[@class='smart-table-header-row']/th/span")
  private List<WebElement> tableHeaderCell;

//  public AngularWebTableHeader readTableHeader() {
//    for (WebElement element : tableHeaderCell) {}
//
//    header =
//        AngularWebTableHeader.builder()
//            .firstNameHeader()
//            .lastNameHeader()
//            .usernameHeader()
//            .roleHeader()
//            .emailHeader()
//            .cellPhoneHeader()
//            .editHeader()
//            .lockedHeader()
//            .editHeader()
//            .deleteHeader()
//            .build();
//  }

  public AngularWebTable readHomepageTable() {
    return AngularWebTable.builder()
        .tableHeader(header)
        .tableLines(tableLines)
        .tableFooter(footer)
        .build();
  }
}
