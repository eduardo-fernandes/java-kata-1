package org.echocat.kata.java.part1.model;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

  private String email;
  private String firstName;
  private String lastName;

  @Override
  public String toString() {
    String message = "Author info";
    if (isNotEmpty(email)) {
      message += " , email: " + email;
    }
    if (isNotEmpty(firstName)) {
      message += " , first name "  + firstName;
    }
    if (isNotEmpty(lastName)) {
      message += " , last name " + lastName;
    }

    return  message;
  }
}
