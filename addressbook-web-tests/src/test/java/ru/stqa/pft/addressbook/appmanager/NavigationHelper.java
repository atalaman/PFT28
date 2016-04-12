package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && (wd.findElement(By.tagName("h1")).getText().equals("Groups"))
            && isElementPresent(By.name("new"))) {
      return;
      }
      click(By.linkText("groups"));
  }


  public void contactPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().contains("add address book entry")) {
      return;
    }
      click(By.linkText("add new"));
  }

  public void HomePage() {
    if(isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void HomePageWithGroup(GroupData groupData) {
    click(By.linkText("home"));
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupData.getName());
  }

  public void HomePageWithAllGroups() {
    click(By.linkText("home"));
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }
}
