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
    type(By.name("mobile"), contactData.getPhone());
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
    returnToHomePage();
  }

  public void deleteByButton(ContactData contact) {
    selectContactByCheckbox(contact.getId());
    deleteSelectedContact();
  }

  public void deleteByEdition(ContactData contact) {
    selectContactByEditIcon(contact.getId());
    deleteEditedContact();
  }

  public void deleteByDetails(ContactData contact) {
    selectContactByDetails(contact.getId());
    editContactFromDetailsPage();
    deleteEditedContact();
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
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
    }
    return contacts;
  }
}
