package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.CSV_DELIMITER;

import org.echocat.kata.java.part1.model.Author;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AuthorRepository {

  private final FileUtils fileUtils = new FileUtils();

  private static final Set<Author> cachedAuthors = new HashSet<>();

  public Author findByEmail(String email) {
    return findAll().stream().filter(a -> a.getEmail().equals(email)).findFirst().orElse(null);
  }

  public Set<Author> findAll() {

    if (!cachedAuthors.isEmpty()) {
      return cachedAuthors;
    }

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/authors.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        cachedAuthors.add(parseAuthor(scanner.nextLine()));
      }
    }

    return cachedAuthors;
  }

  private Author parseAuthor(String authorString) {

    Author author;

    try (Scanner rowScanner = new Scanner(authorString)) {
      rowScanner.useDelimiter(CSV_DELIMITER);
      author = new Author(
          rowScanner.next(),
          rowScanner.next(),
          rowScanner.next());
    }

    return author;
  }
}
