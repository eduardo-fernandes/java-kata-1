package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.COMMA_DELIMITER;

import org.echocat.kata.java.part1.model.Author;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AuthorRepository {

  private FileUtils fileUtils = new FileUtils();

  private static Set<Author> cachedAuthors = new HashSet<>();

  public Author findByEmail(String email) throws FileNotFoundException, URISyntaxException {
    return findAll().stream().filter(a -> a.getEmail().equals(email)).findFirst().orElse(null);
  }

  public Set<Author> findAll() throws FileNotFoundException, URISyntaxException {

    if (!cachedAuthors.isEmpty()) {
      return cachedAuthors;
    }

    Set<Author> authors = new HashSet<>();

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/authors.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        authors.add(parseAuthor(scanner.nextLine()));
      }
    }

    return authors;
  }

  private Author parseAuthor(String authorString) {

    Author author;

    try (Scanner rowScanner = new Scanner(authorString)) {
      rowScanner.useDelimiter(COMMA_DELIMITER);
      author = new Author(
          rowScanner.next(),
          rowScanner.next(),
          rowScanner.next());
    }

    return author;
  }
}
