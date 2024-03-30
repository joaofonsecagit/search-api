package com.app.documentapi.application.document;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.IndexRepository;
import com.app.documentapi.domain.model.Index;
import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.services.DocumentIndexer;
import java.util.*;
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
  private final IndexRepository indexRepository;

  public List<String> indexDocumentsFromDirectory(String directoryPath) {
    log.info("Reading documents from the directory");

    log.debug("Clearing in memory repository to avoid duplicated indexes of the same document");
    documentRepository.clear();

    var documents = fileSystemReader.readDocumentsFromDirectory(directoryPath);
    var documentsNames = new ArrayList<String>();
    var wordToDocument = new HashMap<String, Set<IndexedDocument>>();

    documents.forEach(document -> {
      log.debug("Indexing and storing document: {}", document.name());
      var indexedDocument = documentIndexer.index(document);

      var wordFreqOfIndexDoc = indexedDocument.wordFrequency();

      wordFreqOfIndexDoc.keySet()
          .forEach(given -> {
                var set = wordToDocument.getOrDefault(given, new HashSet<>());
                set.add(indexedDocument);
                wordToDocument.put(given, set);
              }
          );

      documentRepository.add(indexedDocument);
      documentsNames.add(document.name());
    });

    indexRepository.update(Index.builder()
        .wordToDocument(wordToDocument)
        .build());

    log.debug("Successfully stored indexes for {} documents", documents.size());
    return documentsNames;
  }
}