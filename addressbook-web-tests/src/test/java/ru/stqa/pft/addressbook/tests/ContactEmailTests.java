package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends ContactTestsStart {

  public ContactData contact;
  int id;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
    ContactData contact = app.contact().all().iterator().next();
    id = contact.getId();
    if (contact.getAllEmails().isEmpty()) {
      ContactData changedContact = new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
              .withLastName(contact.getLastName()).withAddress(contact.getAddress()).withEmail("123@dr.com")
              .withEmail2("876543rf").withEmail3("ghtjkis");
      app.contact().addEmails(changedContact);
    }
  }

  @Test
  public void testContact(){
    contact = app.contact().retrieveContact(id);
    ContactData contactInfoEmailsFromEditForm = app.contact().infoWithEmailsFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoEmailsFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }
}
