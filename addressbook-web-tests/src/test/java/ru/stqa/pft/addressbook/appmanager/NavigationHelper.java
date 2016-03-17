package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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


  public void gotoNewContactPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().contains("add address book entry")) {
      return;
    }
      click(By.linkText("add new"));
  }

  public void gotoHomePage() {
    if(isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
}
