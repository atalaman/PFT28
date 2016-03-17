package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletionByDeleteButton() {
    app.goTo().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.goTo(), app.group());
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }

  @Test

  public void testContactDeletionByEditIcon() {
    app.goTo().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.goTo(), app.group());
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactForEdition(before.size() - 1);
    app.getContactHelper().deleteEditedContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    app.goTo().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.goTo(), app.group());
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
