package pages;

import components.EditUserComponent;
import components.HomepageTableComponent;
import models.form.EditUserForm;
import models.webTable.AngularWebTable;

public class AngularWebtables {
  private EditUserComponent editUserComponent;
  private HomepageTableComponent homepageTableComponent;

  public AngularWebTable readHomepageTable() {
    return homepageTableComponent.readHomepageTable();
  }

  public EditUserForm readEditUserForm() {
    return editUserComponent.readEditUserForm();
  }
}
