package com.app.documentapi.infrastructure.adapters;

import static java.nio.file.Files.walk;

import com.app.documentapi.application.exception.DocumentReadingException;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileSystemAdapter implements FileSystemReader {

  final Map<String, FileReadingStrategy> strategies = new HashMap<>();

  public FileSystemAdapter() {
    strategies.put("txt", new TextFileReadingStrategy());
  }

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
      throw new DocumentReadingException(directoryPath);
    }

    return documents;
  }

  private void processFile(List<Document> documents, Path filePath) {
    log.debug("Processing file: {}", filePath);
    var fileExtension = getFileExtension(filePath);
    var strategy = strategies.getOrDefault(fileExtension, new DefaultReadingStrategy());
    var document = strategy.readDocument(filePath);
    documents.add(document);
  }

  private String getFileExtension(Path path) {
    var fileName = path.getFileName().toString();
    var dotIndex = fileName.lastIndexOf(".");
    return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
  }
}