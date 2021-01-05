package org.echocat.kata.java.part1.service;

import static org.echocat.kata.java.part1.config.Config.NEW_LINE;

import org.echocat.kata.java.part1.exception.ServiceException;
import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.echocat.kata.java.part1.repository.BookRepository;
import org.echocat.kata.java.part1.repository.MagazineRepository;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PublicationsService {

  private final AuthorRepository authorRepository = new AuthorRepository();
  private final BookRepository bookRepository = new BookRepository();
  private final MagazineRepository magazineRepository = new MagazineRepository();

  public StringBuilder getAllPublicationsOrderedByTitle() throws ServiceException {
    StringBuilder publicationsInfo = new StringBuilder();

    try {
      Set<Book> books = bookRepository.findAll();
      Set<Magazine> magazines = magazineRepository.findAll();

      Set<Publication> publications = new HashSet<>();

      publications.addAll(books);
      publications.addAll(magazines);
      List<Publication> publicationsList =
          publications.stream().sorted(Comparator.comparing(Publication::getTitle)).collect(Collectors.toList());

      publicationsInfo.append("Publications order by title" + NEW_LINE);
      for (Publication publication : publicationsList) {
        publicationsInfo.append(publication).append(NEW_LINE);
      }

    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems finding all publications and sorting them by title", ex);
    }

    return publicationsInfo;
  }

  public StringBuilder getMagazineByIsbn(String isbn) throws ServiceException {
    StringBuilder magazineInfo = new StringBuilder();
    try {
      magazineRepository.findByIsbn(isbn)
          .ifPresentOrElse(
              magazine -> magazineInfo.append("Magazine with isbn ").append(isbn).append(" is ").append(magazine)
                  .append(NEW_LINE),
              () -> magazineInfo.append("No magazine found with isbn ").append(isbn).append(NEW_LINE)
          );
    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems finding a magazine by its isbn: " + isbn, ex);
    }

    return magazineInfo;
  }

  public StringBuilder getBookByIsbn(String isbn) throws ServiceException {
    StringBuilder bookInfo = new StringBuilder();
    try {
      bookRepository.findByIsbn(isbn)
          .ifPresentOrElse(
              book -> bookInfo.append("Book with isbn ").append(isbn).append(" is ").append(book).append(NEW_LINE),
              () -> bookInfo.append("No book found with isbn ").append(isbn).append(NEW_LINE)
          );
    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems finding a book by its isbn: " + isbn, ex);
    }

    return bookInfo;
  }

  public StringBuilder getAuthorsReadableInformation() {

    StringBuilder authorsInfo = new StringBuilder();

    Set<Author> authors = authorRepository.findAll();

    authorsInfo.append("Authors\n");
    authors.forEach(author -> authorsInfo.append(author.toString()).append(NEW_LINE));

    return authorsInfo;
  }

  public StringBuilder getBooksReadableInformation() throws ServiceException {

    StringBuilder booksInfo = new StringBuilder();

    try {
      Set<Book> books = bookRepository.findAll();

      booksInfo.append("Books\n");
      books.forEach(book -> booksInfo.append(book.toString()).append(NEW_LINE));

    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems reading books from cvs file", ex);
    }

    return booksInfo;
  }

  public StringBuilder getMagazinesReadableInformation() throws ServiceException {

    StringBuilder magazinesInfo = new StringBuilder();

    try {
      Set<Magazine> magazines = magazineRepository.findAll();

      magazinesInfo.append("Magazines\n");
      magazines.forEach(magazine -> magazinesInfo.append(magazine.toString()).append(NEW_LINE));

    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems reading books from cvs file", ex);
    }

    return magazinesInfo;
  }
}
