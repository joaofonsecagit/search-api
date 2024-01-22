package com.app.documentapi.application.document;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.services.DocumentIndexer;
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
    log.info("Reading documents from the directory");
    var documents = fileSystemReader.readDocumentsFromDirectory(directoryPath);

    documents.forEach(document -> {
      log.debug("Indexing and storing document: {}", document.name());
      var indexedDocument = documentIndexer.index(document);
      documentRepository.add(indexedDocument);
    });

    log.debug("Successfully stored indexes for {} documents", documents.size());
  }
}