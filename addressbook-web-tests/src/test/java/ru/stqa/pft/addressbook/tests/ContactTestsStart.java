package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactTestsStart extends TestBase{

  protected void createContactForEmptyList() {
    if (app.contact().all().isEmpty()) {
      app.goTo().groupPage();
      if (app.group().all().isEmpty()) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName("Petya").withLastName("Sergeev").withGroup("test1"));
    }
  }
}
