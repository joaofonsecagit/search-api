package com.app.documentapi.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.documentapi.domain.model.IndexedDocument;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryDocumentRepositoryTest {

  private InMemoryDocumentRepository repository;
  private IndexedDocument indexedDocument;
  private UUID documentId;

  @BeforeEach
  void setUp() {
    repository = new InMemoryDocumentRepository();
    documentId = UUID.randomUUID();
    indexedDocument = IndexedDocument.builder()
        .id(documentId)
        .fileName("Test Document")
        .wordFrequency(Map.of("test", 1))
        .build();
  }

  @Test
  void shouldAddAndRetrieveDocument() {
    repository.add(indexedDocument);

    var retrievedDocument = repository.get(documentId);
    assertThat(retrievedDocument).isEqualTo(indexedDocument);
  }

  @Test
  void shouldReturnAllAddedDocuments() {
    repository.add(indexedDocument);

    var allDocuments = repository.getAll();
    assertThat(allDocuments).hasSize(1).contains(indexedDocument);
  }

  @Test
  void shouldDeleteDocument() {
    repository.add(indexedDocument);

    repository.delete(documentId);
    assertThat(repository.get(documentId)).isNull();
  }

  @Test
  void shouldClearAllDocuments() {
    repository.add(indexedDocument);

    repository.clear();
    assertThat(repository.getAll()).isEmpty();
  }

  @Test
  void shouldReturnNullIfDocumentDoesNotExist() {
    assertThat(repository.get(UUID.randomUUID())).isNull();
  }
}