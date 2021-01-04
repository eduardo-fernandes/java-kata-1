package org.echocat.kata.java.part1.model;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
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

  @Override
  public String toString() {
    String message = "Book info";
    if (isNotEmpty(title)) {
      message += " , title " + title;
    }
    if (isNotEmpty(isbn)) {
      message += " , isbn " + isbn;
    }
    if (authors != null && !authors.isEmpty()) {
      message += " , authors " + authors;
    }
    if (isNotEmpty(description)) {
      message += " , description " + description;
    }

    return message;
  }
}
