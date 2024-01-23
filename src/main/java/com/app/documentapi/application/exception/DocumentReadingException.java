package com.app.documentapi.application.exception;

public class DocumentReadingException extends RuntimeException {

  public DocumentReadingException(String path) {
    super("Could not read file in path: %s".formatted(path));
  }
}
