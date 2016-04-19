package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

  private User changePasswordUser = null;
  private static final String ADMIN = "administrator";

  @BeforeMethod
  public void ensurePreconditions() throws MessagingException {
    Users userList = app.db().users();
    User admin = findAdmin(userList);
    Users withoutAdmin = userList.without(admin.withUsername("administrator"));
    String password = "password";
    if(withoutAdmin.isEmpty()) {
      long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost.localdomain", now);
      String user = String.format("user%s", now);
      app.james().createUser(user, password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      changePasswordUser = new User().withUsername(user).withPassword(password).withEmail(email);
    } else {
      changePasswordUser = withoutAdmin.iterator().next();
    }
  }

  @Test
  public void testChangePassword () throws IOException, MessagingException {
    String password = "passworddd";
    resetPasswordByAdmin();
    createSmtpUser(password);
    List<MailMessage> mailMessages = app.james().waitForMail(changePasswordUser.getUsername(), password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, changePasswordUser.getEmail());
    System.out.println(confirmationLink);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(changePasswordUser.getUsername(), password));
  }

  public void createSmtpUser(String newPassword) {
    if (app.james().doesUserExist(changePasswordUser.getUsername())) {
      app.james().deleteUser(changePasswordUser.getUsername());
    }
    app.james().createUser(changePasswordUser.getUsername(), newPassword);
  }

  public void resetPasswordByAdmin() {
    app.sessionHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.getDriver().findElement(By.xpath("//a[text()=\"Manage\"]")).click();
    app.getDriver().findElement(By.xpath("//a[text()=\"Manage Users\"]")).click();
    String xpasForUser = String.format(("//a[text()=\"%s\"]"), changePasswordUser.getUsername());
    app.getDriver().findElement(By.xpath(xpasForUser)).click();
    app.getDriver().findElement(By.cssSelector("input[value = \"Reset Password\"]")).click();
  }

  private User findAdmin(Users users) {
    User admin = null;
    for (User user : users) {
      if (ADMIN.equals(user.getUsername())) {
        admin = user;
        break;
      }
    }
    return admin;
  }
}
