package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (app.getContactHelper().isThereAContact() == false)
      before++;
    app.getContactHelper().checkContactPresence(app.getNavigationHelper(), app.getGroupHelper());
    app.getContactHelper().selectContactForEdition();
    app.getContactHelper().fillContactform(new ContactData("Ivan22", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
