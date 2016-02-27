package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
  private final String username;
  private final String password;
  FirefoxDriver wd;

  public Login(String username, String password, FirefoxDriver wd) {
    this.username = username;
    this.password = password;
    this.wd = wd;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void login() {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(getUsername());
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(getPassword());
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }
}
