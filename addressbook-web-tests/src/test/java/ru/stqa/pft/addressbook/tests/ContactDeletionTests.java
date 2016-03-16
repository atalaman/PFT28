package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)

  public void testContactDeletionByDeleteButton() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test(enabled = false)

  public void testContactDeletionByEditIcon() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContactForEdition(before - 1);
    app.getContactHelper().deleteEditedContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactForDetails(before.size() - 1);
    app.getContactHelper().editContactFromDetailsPage();
    app.getContactHelper().deleteEditedContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}
