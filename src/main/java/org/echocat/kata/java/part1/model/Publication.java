package org.echocat.kata.java.part1.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

  protected String title;
  protected String isbn;
  protected Set<Author> authors;
}
