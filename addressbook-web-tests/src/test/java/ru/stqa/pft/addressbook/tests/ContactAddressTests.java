package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends ContactTestsStart{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
  }

  @Test
  public void testContact(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoAddressFromEditForm = app.contact().infoWithAddressFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoAddressFromEditForm.getAddress()));
  }

}
