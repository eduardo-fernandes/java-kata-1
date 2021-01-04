package org.echocat.kata.java.part1.service;

import org.echocat.kata.java.part1.exception.ServiceException;
import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.echocat.kata.java.part1.repository.BookRepository;
import org.echocat.kata.java.part1.repository.MagazineRepository;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Set;

public class PublicationsService {

  private final AuthorRepository authorRepository = new AuthorRepository();
  private final BookRepository bookRepository = new BookRepository();
  private final MagazineRepository magazineRepository = new MagazineRepository();

  public StringBuilder getAuthorsReadableInformation() throws ServiceException {

    StringBuilder authorsInfo = new StringBuilder();

    try {
      Set<Author> authors = authorRepository.findAll();

      authorsInfo.append("Authors\n");
      authors.forEach(author -> authorsInfo.append(author.toString()).append("\n"));

    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems reading authors from cvs file", ex);
    }

    return authorsInfo;
  }

  public StringBuilder getBooksReadableInformation() throws ServiceException {

    StringBuilder booksInfo = new StringBuilder();

    try {
      Set<Book> books = bookRepository.findAll();

      booksInfo.append("Books\n");
      books.forEach(book -> booksInfo.append(book.toString()).append("\n"));

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
      magazines.forEach(magazine -> magazinesInfo.append(magazine.toString()).append("\n"));

    } catch (FileNotFoundException | URISyntaxException ex) {
      throw new ServiceException("Problems reading books from cvs file", ex);
    }

    return magazinesInfo;
  }
}
