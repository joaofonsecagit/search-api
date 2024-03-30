package com.app.documentapi.infrastructure.repositories;

import com.app.documentapi.domain.IndexRepository;
import com.app.documentapi.domain.model.Index;
import com.app.documentapi.domain.model.IndexedDocument;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryIndexRepository implements IndexRepository {
  private final Map<String, Set<IndexedDocument>> wordToDocument = new HashMap<>();

  @Override
  public void update(Index index) {
    index.wordToDocument().keySet()
        .stream()
        .filter(word -> !wordToDocument.containsKey(word))
        .forEach(wordToAdd -> wordToDocument.put(wordToAdd, index.wordToDocument().get(wordToAdd)));
  }

  @Override
  public Index get() {
    return Index.builder().wordToDocument(wordToDocument).build();
  }
}
