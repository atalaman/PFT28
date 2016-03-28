package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditDetailsTests extends ContactTestsStart {

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
    if (contact.getAllEmails().isEmpty()) {
      ContactData changedContact = new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
              .withLastName(contact.getLastName()).withAddress(contact.getAddress()).withEmail("123@dr.com")
              .withEmail2("876543rf").withEmail3("ghtjkis");
      app.contact().addEmails(changedContact);
    }
    if (contact.getAddress().isEmpty()) {
      ContactData changedContact = new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
              .withLastName(contact.getLastName()).withEmail(contact.getEmail())
              .withAddress("45689 Gorkogo Panasa Street, apt. 85 somewhere\n" + "Moskow Russia 12345");
      app.contact().addAddress(changedContact);
    }
  }

  @Test
  public void testContactEditDetails() {
    contact = app.contact().retrieveContact(id);
    app.contact().selectContactByDetails(contact.getId());
    String content = app.wd.findElement(By.xpath("//div[@id=\"content\"]")).getText();
    app.wd.navigate().back();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(app.contact().allEditDataMerge(contactInfoFromEditForm), equalTo(app.contact().cleanedContent(content)));
  }
}
