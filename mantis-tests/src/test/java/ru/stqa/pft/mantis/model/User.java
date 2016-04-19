package ru.stqa.pft.mantis.model;

import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "mantis_user_table")
public class User {

  @Id
  @Column(name="id")
  private int id;

  @Column(name="username")
  private String username;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public User withId(int id) {
    this.id = id;
    return this;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  public User withEmail(String email) {
    this.email = email;
    return this;
  }

  public User withPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (username != null ? !username.equals(user.username) : user.username != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    return password != null ? password.equals(user.password) : user.password == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
