package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletionByDeleteButton() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().contactCreation(new ContactData("Ivan", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }

  @Test

  public void testContactDeletionByEditIcon() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().contactCreation(new ContactData("Ivan", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", "test1"), true);
    }
    app.getContactHelper().selectContactForEdition();
    app.getContactHelper().deleteEditedContact();
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().contactCreation(new ContactData("Ivan", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", "test1"), true);
    }
    app.getContactHelper().selectContactForDetails();
    app.getContactHelper().editContactFromDetailsPage();
    app.getContactHelper().deleteEditedContact();
  }
}
