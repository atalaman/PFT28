package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoNewContactPage();
        fillContactform(new ContactData("Ivan", "Petrov", "Somewhere", "+19012345678", "ipetrov@gmail.com"));
        submitContactCreation();
        returnToHomePage();
    }

    public void returnToHomePage() {
        app.wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        app.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void fillContactform(ContactData contactData) {
        app.wd.findElement(By.name("firstname")).click();
        app.wd.findElement(By.name("firstname")).clear();
        app.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        app.wd.findElement(By.name("lastname")).click();
        app.wd.findElement(By.name("lastname")).clear();
        app.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        app.wd.findElement(By.name("address")).click();
        app.wd.findElement(By.name("address")).clear();
        app.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        app.wd.findElement(By.name("mobile")).click();
        app.wd.findElement(By.name("mobile")).clear();
        app.wd.findElement(By.name("mobile")).sendKeys(contactData.getPhone());
        app.wd.findElement(By.name("email")).click();
        app.wd.findElement(By.name("email")).clear();
        app.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void gotoNewContactPage() {
        app.wd.findElement(By.linkText("add new")).click();
    }
}
