package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletionByDeleteButton() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }

  @Test

  public void testContactDeletionByEditIcon() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContactForEdition();
    app.getContactHelper().deleteEditedContact();
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContactForDetails();
    app.getContactHelper().editContactFromDetailsPage();
    app.getContactHelper().deleteEditedContact();
  }
}
