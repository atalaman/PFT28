package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void encurePrecondition() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if(!app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().contactCreation(new ContactData("Ivan"
              , "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", "test1"), true);
    }
  }

  @Test
  public void testContactDeletionByDeleteButton() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }

  @Test
  public void testContactDeletionByEditIcon() {
    app.getContactHelper().selectContactForEdition();
    app.getContactHelper().deleteEditedContact();
  }

  @Test
  public void testContactDeletionByDetailsIcon() {
    app.getContactHelper().selectContactForDetails();
    app.getContactHelper().editContactFromDetailsPage();
    app.getContactHelper().deleteEditedContact();
  }
}
