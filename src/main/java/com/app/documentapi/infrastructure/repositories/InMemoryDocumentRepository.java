package com.app.documentapi.infrastructure.repositories;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.model.IndexedDocument;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

  private final Map<UUID, IndexedDocument> indexedDocuments = new HashMap<>();

  @Override
  public void add(IndexedDocument indexedDocument) {
    indexedDocuments.put(indexedDocument.id(), indexedDocument);
  }

  @Override
  public IndexedDocument get(UUID id) {
    return indexedDocuments.get(id);
  }

  @Override
  public List<IndexedDocument> getAll() {
    return List.copyOf(indexedDocuments.values());
  }

  @Override
  public void delete(UUID id) {
    indexedDocuments.remove(id);
  }

  @Override
  public void clear() {
    indexedDocuments.clear();
  }
}
