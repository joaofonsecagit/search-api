package com.app.documentapi.application.document;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.services.DocumentIndexer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentIndexingService {

  private final FileSystemReader fileSystemReader;
  private final DocumentIndexer documentIndexer;
  private final DocumentRepository documentRepository;

  public void indexDocumentsFromDirectory(String directoryPath) {
    // Read documents from the directory
    log.info("READING");
    List<Document> documents = fileSystemReader.readDocumentsFromDirectory(directoryPath);
    log.info("READ: {}", documents);

    // Index each document and store it in the repository
    for (Document document : documents) {
      IndexedDocument indexedDocument = documentIndexer.index(document);
      documentRepository.add(indexedDocument);
    }
  }
}