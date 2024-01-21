package com.app.documentapi.application.document;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.services.DocumentIndexer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentIndexingService {

  private final FileSystemReader fileSystemReader;
  private final DocumentIndexer documentIndexer;
  private final DocumentRepository documentRepository;

  public void indexDocumentsFromDirectory(String directoryPath) {
    // Read documents from the directory
    List<Document> documents = fileSystemReader.readDocumentsFromDirectory(directoryPath);

    // Index each document and store it in the repository
    for (Document document : documents) {
      IndexedDocument indexedDocument = documentIndexer.index(document);
      documentRepository.add(indexedDocument);
    }
  }
}