package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends ContactTestsStart{

  public ContactData contact;
  int id;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
    ContactData contact = app.contact().all().iterator().next();
    id = contact.getId();
    if (contact.getAddress().isEmpty()) {
      ContactData changedContact = new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
              .withLastName(contact.getLastName()).withEmail(contact.getEmail())
              .withAddress("45689 Gorkogo Panasa Street, apt. 85 somewhere\n" + "Moskow Russia 12345");
      app.contact().addAddress(changedContact);
    }
  }

  @Test
  public void testContact(){
    contact = app.contact().retrieveContact(id);
    ContactData contactInfoAddressFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoAddressFromEditForm.getAddress()));
  }

}
