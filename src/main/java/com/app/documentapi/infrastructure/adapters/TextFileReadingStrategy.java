package com.app.documentapi.infrastructure.adapters;

import static java.nio.file.Files.readString;
import static java.util.UUID.randomUUID;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.nio.file.Path;
import lombok.SneakyThrows;

public class TextFileReadingStrategy implements FileReadingStrategy {

  @SneakyThrows
  @Override
  public Document readDocument(Path filePath) {
    var content = readString(filePath);
    return Document.builder()
        .id(randomUUID())
        .name(filePath.getFileName().toString())
        .content(content)
        .build();
  }
}