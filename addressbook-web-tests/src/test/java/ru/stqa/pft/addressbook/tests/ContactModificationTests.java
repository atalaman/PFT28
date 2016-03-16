package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactForEdition(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Ivan22", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", null);
    app.getContactHelper().fillContactform(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
