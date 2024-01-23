package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.nio.file.Path;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultReadingStrategy implements FileReadingStrategy {

  @SneakyThrows
  @Override
  public Document readDocument(Path filePath) {
    log.warn("Unsupported file type!");
    return new Document(UUID.randomUUID(), filePath.getFileName().toString(), "Unsupported file type.");
  }
}
