package com.app.documentapi.infrastructure.adapters;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileSystemAdapter implements FileSystemReader {

  private final Map<String, FileReadingStrategy> strategies = new HashMap<>();

  public FileSystemAdapter() {
    strategies.put("txt", new TextFileReadingStrategy());
    // Add other strategies here
  }

  public List<Document> readDocumentsFromDirectory(String directoryPath) {
    log.info("DIRECTORY READING.....");
    List<Document> documents = new ArrayList<>();
    try {
      Files.walk(Path.of(directoryPath))
          .filter(Files::isRegularFile)
          .forEach(filePath -> {
            try {
              String fileExtension = getFileExtension(filePath);
              FileReadingStrategy strategy = strategies.getOrDefault(fileExtension, new DefaultReadingStrategy());
              Document document = strategy.readDocument(filePath);
              documents.add(document);
            } catch (IOException e) {
              log.error("Error reading file: " + filePath, e);
            }
          });
    } catch (IOException e) {
      log.error("Error accessing directory: " + directoryPath, e);
    }
    log.info("returning...");
    return documents;
  }

  private String getFileExtension(Path path) {
    String fileName = path.getFileName().toString();
    int dotIndex = fileName.lastIndexOf(".");
    return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
  }
}