package org.echocat.kata.java.part1.model;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Magazine extends Publication {

  private String publishedAt;

  public Magazine(String title,
                  String isbn,
                  Set<Author> authors,
                  String publishedAt) {
    super(title, isbn, authors);
    this.publishedAt = publishedAt;
  }

  @Override
  public String toString() {
    String message = "Magazine info";
    if (isNotEmpty(title)) {
      message += " , title " + title;
    }
    if (isNotEmpty(isbn)) {
      message += " , isbn " + isbn;
    }
    if (authors != null && !authors.isEmpty()) {
      message += " , authors " + authors;
    }
    if (isNotEmpty(publishedAt)) {
      message += " , published At " + publishedAt;
    }

    return message;
  }
}
