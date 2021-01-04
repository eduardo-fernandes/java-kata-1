package org.echocat.kata.java.part1.repository;

import java.io.InputStream;

public class FileUtils {

  public InputStream getFile(String pathInResource) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(pathInResource);
  }
}
