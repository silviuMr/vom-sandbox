package frontEnd.components;

import frontEnd.models.protractorApp.webTable.AngularWebTable;
import frontEnd.models.protractorApp.webTable.AngularWebTableLine;
import frontEnd.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class HomepageTableComponent extends BasePage {

  @FindBy(xpath = "//table")
  private WebElement table;

  @FindBy(xpath = "//tbody/tr")
  private List<WebElement> tableLineList;

  public HomepageTableComponent(WebDriver webDriver) {
    super(webDriver);
  }

  public AngularWebTable readHomepageTable() {
    waitUntilVisible(table);
    List<AngularWebTableLine> tableLines = new ArrayList<>();

    for (int i=0;i<tableLineList.size(); i++) {
      String lineSelector = "//tbody/tr[%d]/td[%d]";
      String lockedCheckboxSelector = lineSelector + "/input[@type='checkbox']";
      String editButtonSelector = lineSelector + "/button[@type='edit']";
      String deleteButtonSelector = lineSelector + "/button[@ng-click='delUser()']";

      AngularWebTableLine currentLine = AngularWebTableLine.builder()
              .firstName(driver.findElement(By.xpath(format(lineSelector, i+1, 1))).getText())
              .lastName(driver.findElement(By.xpath(format(lineSelector, i+1, 2))).getText())
              .userName(driver.findElement(By.xpath(format(lineSelector, i+1, 3))).getText())
              .customer(driver.findElement(By.xpath(format(lineSelector, i+1, 5))).getText())
              .role(driver.findElement(By.xpath(format(lineSelector, i+1, 6))).getText())
              .email(driver.findElement(By.xpath(format(lineSelector, i+1, 7))).getText())
              .cellPhone(driver.findElement(By.xpath(format(lineSelector, i+1, 8))).getText())
              .locked(driver.findElement(By.xpath(format(lockedCheckboxSelector, i+1, 9))).isEnabled())
              .editUserButton(driver.findElement(By.xpath(format(editButtonSelector, i+1, 10))))
              .deleteUserButton(driver.findElement(By.xpath(format(deleteButtonSelector, i+1, 11))))
              .build();
      tableLines.add(currentLine);
    }

    return AngularWebTable.builder()
        .tableLines(tableLines)
        .build();
  }
}
