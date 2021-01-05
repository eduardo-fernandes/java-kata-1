package org.echocat.kata.java.part1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

  private String email;
  private String firstName;
  private String lastName;
}
