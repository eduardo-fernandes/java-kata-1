package org.echocat.kata.java.part1.exception;

public class ServiceException extends Exception {

  public ServiceException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
