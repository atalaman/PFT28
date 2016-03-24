package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactform(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContactByEditIcon(int id) {
    wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"]")).click();
  }

  public void selectContactByCheckbox(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectContactByDetails(int id) {
    wd.findElement(By.cssSelector("a[href=\"view.php?id=" + id + "\"]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void modify(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    fillContactform(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void addPhones(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    fillPhones(contact);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  private void fillPhones(ContactData contact) {
    type(By.name("home"), contact.getHomePhone());
    type(By.name("mobile"), contact.getMobilePhone());
    type(By.name("work"), contact.getWorkPhone());
  }

  public void addEmails(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    fillEmails(contact);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  private void fillEmails(ContactData contact) {
    type(By.name("email"), contact.getEmail());
    type(By.name("email2"), contact.getEmail2());
    type(By.name("email3"), contact.getEmail3());
  }

  public void addAddress(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    fillAddress(contact);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  private void fillAddress(ContactData contact) {
    type(By.name("address"), contact.getAddress());
  }

  public void deleteByButton(ContactData contact) {
    selectContactByCheckbox(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public void deleteByEdition(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    deleteEditedContact();
    contactCache = null;
  }

  public void deleteByDetails(ContactData contact) {
    selectContactByDetails(contact.getId());
    editContactFromDetailsPage();
    deleteEditedContact();
    contactCache = null;
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void deleteEditedContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void editContactFromDetailsPage() {
    click(By.name("modifiy"));
  }

  public void create(ContactData contact) {
    fillContactform(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public ContactData retrieveContact(int id) {
    ContactData contact = null;
    Contacts changedContacts = all();
    for (ContactData cont : changedContacts) {
      if (cont.getId() == id) {
        contact = cont;
      }
    }
    return contact;
  }

  public ContactData infoWithPhonesFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname)
            .withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  public ContactData infoWithEmailsFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname)
            .withLastName(lastname).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoWithAddressFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.cssSelector("#content>form>textarea")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname)
            .withLastName(lastname).withAddress(address);
  }

  private void initContactModificationById(int id) {
   /* WebElement chechbox = wd.findElement(By.cssSelector(String.format("input[value'%s']", id)));
    WebElement row = chechbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/

    //wd.findElement(By.xpath(String.format("//input[value='%s']/../../td[8]/a",id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']]/td[8]/a",id))).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
  }
}
