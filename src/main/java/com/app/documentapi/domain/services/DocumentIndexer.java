package com.app.documentapi.domain.services;

import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentIndexer {

  public IndexedDocument index(Document document) {
    // Implement the indexing logic here
    // For example, tokenize the document content,
    // count word occurrences, and store them in the IndexedDocument
    return null;
  }
}
