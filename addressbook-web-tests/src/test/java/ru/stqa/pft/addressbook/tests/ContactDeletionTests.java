package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)

  public void testContactDeletionByDeleteButton() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact() == false)
      before++;
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test(enabled = false)

  public void testContactDeletionByEditIcon() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact() == false)
      before++;
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContactForEdition(before - 1);
    app.getContactHelper().deleteEditedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact() == false)
      before++;
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContactForDetails(before - 1);
    app.getContactHelper().editContactFromDetailsPage();
    app.getContactHelper().deleteEditedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
