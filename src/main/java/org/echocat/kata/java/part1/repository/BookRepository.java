package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.CSV_DELIMITER;
import static org.echocat.kata.java.part1.config.Config.EMAIL_DELIMITER;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository {

  private final AuthorRepository authorRepository = new AuthorRepository();

  private final FileUtils fileUtils = new FileUtils();

  private static final Set<Book> cachedBooks = new HashSet<>();

  public Optional<Book> findByIsbn(String isbn) throws FileNotFoundException, URISyntaxException {
    return findAll().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
  }

  public Set<Book> findAll() throws FileNotFoundException, URISyntaxException {

    if (!cachedBooks.isEmpty()) {
      return cachedBooks;
    }

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/books.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        cachedBooks.add(parseBook(scanner.nextLine()));
      }
    }

    return cachedBooks;
  }

  private Set<Author> parseAuthors(String emailsString) throws FileNotFoundException, URISyntaxException {
    Set<Author> authors = new HashSet<>();

    String[] emails = emailsString.split(EMAIL_DELIMITER);
    for (String email : emails) {
      authors.add(authorRepository.findByEmail(email));
    }

    return authors;
  }

  private Book parseBook(String bookString) throws FileNotFoundException, URISyntaxException {

    Book book;

    try (Scanner rowScanner = new Scanner(bookString)) {
      rowScanner.useDelimiter(CSV_DELIMITER);
      book = new Book(
          rowScanner.next(),
          rowScanner.next(),
          parseAuthors(rowScanner.next()),
          rowScanner.next());
    }

    return book;
  }
}
