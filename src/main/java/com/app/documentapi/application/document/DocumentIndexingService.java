package com.app.documentapi.application.document;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.services.DocumentIndexer;
import java.util.ArrayList;
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

  public IndexDto indexDocumentsFromDirectory(String directoryPath) {
    log.info("Reading documents from the directory");
    var documents = fileSystemReader.readDocumentsFromDirectory(directoryPath);
    var documentsNames = new ArrayList<String>();

    documents.forEach(document -> {
      log.debug("Indexing and storing document: {}", document.name());
      var indexedDocument = documentIndexer.index(document);
      documentRepository.add(indexedDocument);
      documentsNames.add(document.name());
    });

    log.debug("Successfully stored indexes for {} documents", documents.size());
    return IndexDto.builder()
        .directoryPath(directoryPath)
        .indexedDocuments(documentsNames)
        .build();
  }
}