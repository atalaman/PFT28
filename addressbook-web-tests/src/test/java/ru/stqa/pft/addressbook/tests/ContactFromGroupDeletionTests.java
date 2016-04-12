package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFromGroupDeletionTests extends ContactTestsStart{

  private ContactData changedContact = null;
  private GroupData deletedGroup = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    createContactForEmptyList();
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    int n = 0;
    for (ContactData contact : contacts) {
      Groups thisContactGroups = contact.getGroups();
      if (!thisContactGroups.isEmpty()) {
        deletedGroup = thisContactGroups.iterator().next();
        changedContact = contact;
        n = 1;
        break;
      }
    }
    if (n == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test99"));
      Groups groupsAfterAdd = app.db().groups();
      for(GroupData group : groupsAfterAdd){
        if (group.getName().equals("test99")){
          deletedGroup = group;
          break;
        }
      }
      changedContact = contacts.iterator().next();
      app.goTo().HomePageWithAllGroups();
      app.contact().selectContactByCheckbox(changedContact.getId());
      app.contact().selectGroupForAdditionFromTheList(deletedGroup);
      app.contact().addContactToGroup();
    }
  }

  @Test
  public void testContactFromGroupDeletion() {
    Groups groupsForContactBefore = changedContact.getGroups();
    app.goTo().HomePageWithGroup(deletedGroup);
    Contacts contactsForGroupFromUiBefore = new Contacts(app.contact().all().stream().map((g) -> new ContactData().withId(g.getId())
            .withLastName(g.getLastName()).withFirstName(g.getFirstName())).collect(Collectors.toSet()));

    app.contact().selectContactByCheckbox(changedContact.getId());
    app.contact().deleteContactFromGroup();

    Contacts after = app.db().contacts();
    ContactData renewedContact = null;
    for(ContactData contact : after){
      if(contact.getId() == changedContact.getId()){
        renewedContact = contact;
        break;
      }
    }
    Groups groupsForContactAfter = renewedContact.getGroups();
    assertThat(groupsForContactAfter, equalTo(groupsForContactBefore.without(deletedGroup)));

    app.goTo().HomePageWithGroup(deletedGroup);
    app.contact().resetContactCache();
    Contacts contactsForGroupFromUiAfter = new Contacts(app.contact().all().stream().map((g) -> new ContactData().withId(g.getId())
            .withLastName(g.getLastName()).withFirstName(g.getFirstName())).collect(Collectors.toSet()));
    ContactData exam = new ContactData().withId(changedContact.getId()).withFirstName(changedContact.getFirstName())
            .withLastName(changedContact.getLastName());
    assertThat(contactsForGroupFromUiAfter, equalTo(contactsForGroupFromUiBefore.without(exam)));
  }
}
