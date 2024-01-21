package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class DefaultReadingStrategy implements FileReadingStrategy {

  @Override
  public Document readDocument(Path filePath) throws IOException {
    // Handle unknown file type or implement a default behavior
    // For example, read as a plain text file or log an error
    return new Document(UUID.randomUUID(), filePath.getFileName().toString(), "Unsupported file type.");
  }
}
