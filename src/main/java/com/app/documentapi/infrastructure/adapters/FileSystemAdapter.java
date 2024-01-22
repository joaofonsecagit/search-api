package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.walk;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileSystemAdapter implements FileSystemReader {

  private final Map<String, FileReadingStrategy> strategies;

  @Override
  public List<Document> readDocumentsFromDirectory(String directoryPath) {
    log.info("Reading directory: {}", directoryPath);
    var documents = new ArrayList<Document>();
    try {
      walk(Path.of(directoryPath))
          .filter(Files::isRegularFile)
          .forEach(filePath -> processFile(documents, filePath));
    } catch (IOException e) {
      log.error("Error accessing directory: {}", directoryPath, e);
    }

    return documents;
  }

  private void processFile(List<Document> documents, Path filePath) {
    log.debug("Processing file: {}", filePath);
    try {
      var fileExtension = getFileExtension(filePath);
      var strategy = strategies.getOrDefault(fileExtension, new DefaultReadingStrategy());
      var document = strategy.readDocument(filePath);
      documents.add(document);
    } catch (IOException e) {
      log.error("Error reading file: {}", filePath, e);
    }
  }

  private String getFileExtension(Path path) {
    var fileName = path.getFileName().toString();
    var dotIndex = fileName.lastIndexOf(".");
    return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
  }
}