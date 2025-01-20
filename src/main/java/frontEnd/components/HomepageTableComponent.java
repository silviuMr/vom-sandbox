package frontEnd.components;

import java.util.List;
import frontEnd.models.protractorApp.webTable.AngularWebTable;
import frontEnd.models.protractorApp.webTable.AngularWebTableFooter;
import frontEnd.models.protractorApp.webTable.AngularWebTableHeader;
import frontEnd.models.protractorApp.webTable.AngularWebTableLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import frontEnd.pages.BasePage;

public class HomepageTableComponent extends BasePage {
  private AngularWebTableHeader header;
  private AngularWebTableFooter footer;
  private List<AngularWebTableLine> tableLines;

  @FindBy(xpath = "//table")
  private WebElement table;

  @FindBy(xpath = "//thead/tr[@class='smart-table-header-row']/th/span")
  private List<WebElement> tableHeaderCell;

  public HomepageTableComponent(WebDriver webDriver) {
    super(webDriver);
  }

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
    waitUntilVisible(table);
    return AngularWebTable.builder()
        .tableHeader(header)
        .tableLines(tableLines)
        .tableFooter(footer)
        .build();
  }
}
