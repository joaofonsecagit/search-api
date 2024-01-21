package com.app.documentapi.domain;

import com.app.documentapi.domain.model.IndexedDocument;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository {

  void add(IndexedDocument indexedDocument);

  IndexedDocument get(UUID id);

  List<IndexedDocument> getAll();

  void delete(UUID id);

  void clear();
}