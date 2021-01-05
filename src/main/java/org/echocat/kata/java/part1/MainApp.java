package org.echocat.kata.java.part1;

import static java.lang.System.out;

import org.echocat.kata.java.part1.exception.ServiceException;
import org.echocat.kata.java.part1.service.PublicationsService;

public class MainApp {

  private static final PublicationsService publicationsSerivce = new PublicationsService();

  public static void main(String[] args) {

    out.println(getAuthors());

    try {
      out.println(getBooks());
      out.println(getMagazines());
      out.println(getBookByValidIsbn());
      out.println(getMagazineByValidIsbn());
      out.println(getBookByInvalidIsbn());
      out.println(getMagazineByInvalidIsbn());
      out.println(getAllPublicationsOrderedByTitle());
    } catch (ServiceException e) {
      e.printStackTrace();
    }
  }

  protected static String getAllPublicationsOrderedByTitle() throws ServiceException {
    return publicationsSerivce.getAllPublicationsOrderedByTitle().toString();
  }

  protected static String getMagazineByInvalidIsbn() throws ServiceException {
    return publicationsSerivce.getMagazineByIsbn("abc").toString();
  }

  protected static String getBookByInvalidIsbn() throws ServiceException {
    return publicationsSerivce.getBookByIsbn("abc").toString();
  }

  protected static String getMagazineByValidIsbn() throws ServiceException {
    return publicationsSerivce.getMagazineByIsbn("2365-8745-7854").toString();
  }

  protected static String getBookByValidIsbn() throws ServiceException {
    return publicationsSerivce.getBookByIsbn("4545-8558-3232").toString();
  }

  protected static String getAuthors() {
    return publicationsSerivce.getAuthorsReadableInformation().toString();
  }

  protected static String getBooks() throws ServiceException {
    return publicationsSerivce.getBooksReadableInformation().toString();
  }

  protected static String getMagazines() throws ServiceException {
    return publicationsSerivce.getMagazinesReadableInformation().toString();
  }

  protected static String getHelloWorldText() {
    return "Hello world!";
  }

}
