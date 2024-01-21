package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class TextFileReadingStrategy implements FileReadingStrategy {

  @Override
  public Document readDocument(Path filePath) throws IOException {
    String content = Files.readString(filePath);
    return new Document(UUID.randomUUID(), filePath.getFileName().toString(), content);
  }
}

// Similarly, you can create other strategies like PdfFileReadingStrategy, DocxFileReadingStrategy, etc.
