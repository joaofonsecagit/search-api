package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static java.util.UUID.randomUUID;

public class TextFileReadingStrategy implements FileReadingStrategy {

  @Override
  public Document readDocument(Path filePath) throws IOException {
    var content = readString(filePath);
    return Document.builder()
        .id(randomUUID())
        .name(filePath.getFileName().toString())
        .content(content)
        .build();
  }
}