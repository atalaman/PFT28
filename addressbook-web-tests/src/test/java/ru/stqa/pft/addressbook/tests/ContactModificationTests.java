package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends ContactTestsStart{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    System.out.println(before);
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Timur").withMiddleName("Ter")
            .withLastName("Mamaev").withAddress("Nowhere"). withHomePhone("888777999").withMobilePhone("111222333")
            .withWorkPhone("78956321").withEmail("erfnvwerjonbower").withEmail2("hj123").withEmail3("lkl");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    System.out.println(after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
