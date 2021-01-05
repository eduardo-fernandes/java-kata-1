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
public class Magazine extends Publication {

  private String publishedAt;

  public Magazine(String title,
                  String isbn,
                  Set<Author> authors,
                  String publishedAt) {
    super(title, isbn, authors);
    this.publishedAt = publishedAt;
  }
}
