package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.UUID;

@Slf4j
public class DefaultReadingStrategy implements FileReadingStrategy {

  @Override
  public Document readDocument(Path filePath) {
    log.warn("Unsupported file type!");
    return new Document(UUID.randomUUID(), filePath.getFileName().toString(), "Unsupported file type.");
  }
}
