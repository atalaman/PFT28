package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends ContactTestsStart {

  public ContactData contact;
  int id;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
    ContactData contact = app.contact().all().iterator().next();
    id = contact.getId();
    if (contact.getAllPhones().isEmpty()) {
      ContactData changedContact = new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
              .withLastName(contact.getLastName()).withAddress(contact.getAddress()).withEmail(contact.getEmail())
              .withHomePhone("+1(724)9546780").withMobilePhone("333-33-33").withWorkPhone("112233");
      app.contact().addPhones(changedContact);
    }
  }

  @Test
  public void testContactPhones() {
    contact = app.contact().retrieveContact(id);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
  }
}
