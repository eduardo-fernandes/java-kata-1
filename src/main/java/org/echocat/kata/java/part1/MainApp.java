package org.echocat.kata.java.part1;

import static java.lang.System.*;

import org.echocat.kata.java.part1.exception.ServiceException;
import org.echocat.kata.java.part1.service.PublicationsService;

public class MainApp {

  private static final PublicationsService publicationsSerivce = new PublicationsService();

  public static void main(String[] args) {
    try {
      out.println(getAuthors());
      out.println("\n\n");
      out.println(getBooks());
      out.println("\n\n");
      out.println(getMagazines());
    } catch (ServiceException e) {
      e.printStackTrace();
    }
  }

  protected static String getAuthors() throws ServiceException {
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
