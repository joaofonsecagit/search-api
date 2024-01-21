package com.app.documentapi.infrastructure.adapters;

import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.model.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileSystemAdapter implements FileSystemReader {

  public List<Document> readDocumentsFromDirectory(String directoryPath) {
    List<Document> documents = new ArrayList<>();
    try {
      Files.walk(Path.of(directoryPath))
          .filter(Files::isRegularFile)
          .forEach(filePath -> {
            try {
              String content = Files.readString(filePath);
              documents.add(new Document(UUID.randomUUID(), filePath.getFileName().toString(), content));
            } catch (IOException e) {
              log.error("Error reading file: " + filePath, e);
            }
          });
    } catch (IOException e) {
      log.error("Error accessing directory: " + directoryPath, e);
    }
    return documents;
  }
}