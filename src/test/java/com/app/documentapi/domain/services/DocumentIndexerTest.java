package com.app.documentapi.domain.services;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.app.documentapi.domain.model.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentIndexerTest {

  @Mock
  private DocumentNormaliser documentNormaliser;
  @InjectMocks
  private DocumentIndexer documentIndexer;

  @Test
  void shouldIndexDocumentCorrectly() {
    // Given
    var documentName = "testDocument";
    var documentContent = "Hello world, hello";
    var document = new Document(randomUUID(), documentName, documentContent);
    String[] tokens = {"hello", "world", "hello"};
    when(documentNormaliser.tokenize(documentContent)).thenReturn(tokens);

    // When
    var indexedDocument = documentIndexer.index(document);

    // Then
    assertThat(indexedDocument).isNotNull();
    assertThat(indexedDocument.id()).isNotNull();
    assertThat(indexedDocument.fileName()).isEqualTo(documentName);
    assertThat(indexedDocument.wordFrequency()).hasSize(2)
        .containsEntry("hello", 2)
        .containsEntry("world", 1);
  }
}