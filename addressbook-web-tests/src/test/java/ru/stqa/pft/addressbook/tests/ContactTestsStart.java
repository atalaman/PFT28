package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

public class ContactTestsStart extends TestBase{

  protected void createContactForEmptyList() {
    if (app.db().contacts().isEmpty()) {
      app.goTo().groupPage();
      if (app.db().groups().isEmpty()) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().contactPage();
      File photo = new File("src/test/resources/Photo.png");
      app.contact().create(new ContactData().withFirstName("Petya").withMiddleName("Ter").withLastName("Sergeev")
              .withAddress("Nowhere"). withHomePhone("888777999").withMobilePhone("111222333").withWorkPhone("78956321")
              .withEmail("erfnvwerjonbower").withEmail2("hj123").withEmail3("lkl").withGroup("test1").withPhoto(photo));
    }
  }
}
