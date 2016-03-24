package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends ContactTestsStart {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
  }

  @Test

  public void testContactDeletionByDeleteButton() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteByButton(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }


  @Test

  public void testContactDeletionByEditIcon() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteByEdition(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

  @Test

  public void testContactDeletionByDetailsIcon() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteByDetails(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
