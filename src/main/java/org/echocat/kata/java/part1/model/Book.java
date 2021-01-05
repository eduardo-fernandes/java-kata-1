package org.echocat.kata.java.part1.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
public class Book extends Publication {

  private String description;

  public Book(String title,
              String isbn,
              Set<Author> authors,
              String description) {
    super(title, isbn, authors);
    this.description = description;
  }
}
