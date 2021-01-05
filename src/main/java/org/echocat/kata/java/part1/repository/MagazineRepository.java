package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.CSV_DELIMITER;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Magazine;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MagazineRepository {

  private final AuthorRepository authorRepository = new AuthorRepository();

  private final FileUtils fileUtils = new FileUtils();

  private final Set<Magazine> cachedMagazines = new HashSet<>();

  public Optional<Magazine> findByIsbn(String isbn) throws FileNotFoundException, URISyntaxException {
    return findAll().stream().filter(magazine -> magazine.getIsbn().equals(isbn)).findFirst();
  }

  public Set<Magazine> findAll() throws FileNotFoundException, URISyntaxException {

    if (!cachedMagazines.isEmpty()) {
      return cachedMagazines;
    }

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/magazines.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        cachedMagazines.add(parseMagazine(scanner.nextLine()));
      }
    }

    return cachedMagazines;
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
      rowScanner.useDelimiter(CSV_DELIMITER);
      magazine = new Magazine(
          rowScanner.next(),
          rowScanner.next(),
          parseAuthors(rowScanner.next()),
          rowScanner.next());
    }

    return magazine;
  }
}
