package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

  public void selectContactForEdition(int index) {
    index += 2;
    wd.findElement(By. xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void deleteEditedContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void selectContactForDetails(int index) {
    index += 2;
    wd.findElement(By. xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[7]/a/img")).click();
  }

  public void editContactFromDetailsPage() {
    click(By.name("modifiy"));
  }

  public void contactCreation(ContactData contact, boolean creation) {
    fillContactform(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

   public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void checkContactPresence(NavigationHelper n, GroupHelper g){
    if (!isThereAContact()) {
      n.groupPage();
      if(!g.isThereAGroup()){
        g.create(new GroupData().withName("test1"));
      }
      n.gotoNewContactPage();
      contactCreation(new ContactData("Ivan", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com", "test1"), true);
    }
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
