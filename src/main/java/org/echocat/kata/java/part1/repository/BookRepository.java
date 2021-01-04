package org.echocat.kata.java.part1.repository;

import static org.echocat.kata.java.part1.config.Config.COMMA_DELIMITER;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookRepository {

  private final AuthorRepository authorRepository = new AuthorRepository();

  private final FileUtils fileUtils = new FileUtils();

  private static Set<Book> cachedBooks = new HashSet<>();

  public Set<Book> findAll() throws FileNotFoundException, URISyntaxException {

    if (!cachedBooks.isEmpty()) {
      return cachedBooks;
    }

    Set<Book> books = new HashSet<>();

    try (Scanner scanner = new Scanner(fileUtils.getFile("org/echocat/kata/java/part1/data/books.csv"));) {

      // skip header
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        books.add(parseBook(scanner.nextLine()));
      }
    }

    return books;
  }

  private Set<Author> parseAuthors(String emailsString) throws FileNotFoundException, URISyntaxException {
    Set<Author> authors = new HashSet<>();

    String[] emails = emailsString.split(";");
    for (String email : emails) {
      authors.add(authorRepository.findByEmail(email));
    }

    return authors;
  }

  private Book parseBook(String bookString) throws FileNotFoundException, URISyntaxException {

    Book book;

    try (Scanner rowScanner = new Scanner(bookString)) {
      rowScanner.useDelimiter(COMMA_DELIMITER);
      book = new Book(
          rowScanner.next(),
          rowScanner.next(),
          parseAuthors(rowScanner.next()),
          rowScanner.next());
    }

    return book;
  }
}
