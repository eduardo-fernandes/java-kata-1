package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.COMMA_DELIMITER;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Magazine;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagazineRepository {

  private final AuthorRepository authorRepository = new AuthorRepository();

  private final FileUtils fileUtils = new FileUtils();

  public Set<Magazine> findAll() throws FileNotFoundException, URISyntaxException {

    Set<Magazine> magazines = new HashSet<>();

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/magazines.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        magazines.add(parseMagazine(scanner.nextLine()));
      }
    }

    return magazines;
  }

  private Set<Author> parseAuthors(String emailsString) throws FileNotFoundException, URISyntaxException {
    Set<Author> authors = new HashSet<>();

    String[] emails = emailsString.split(";");
    for (String email : emails) {
      authors.add(authorRepository.findByEmail(email));
    }

    return authors;
  }

  private Magazine parseMagazine(String magazineString) throws FileNotFoundException, URISyntaxException {

    Magazine magazine;

    try (Scanner rowScanner = new Scanner(magazineString)) {
      rowScanner.useDelimiter(COMMA_DELIMITER);
      magazine = new Magazine(
          rowScanner.next(),
          rowScanner.next(),
          parseAuthors(rowScanner.next()),
          rowScanner.next());
    }

    return magazine;
  }
}
