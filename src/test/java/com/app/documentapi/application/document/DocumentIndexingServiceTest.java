package com.app.documentapi.application.document;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.FileSystemReader;
import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.services.DocumentIndexer;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentIndexingServiceTest {

  @Mock
  private FileSystemReader fileSystemReader;
  @Mock
  private DocumentIndexer documentIndexer;
  @Mock
  private DocumentRepository documentRepository;
  @InjectMocks
  private DocumentIndexingService documentIndexingService;

  @Test
  @Disabled
  void shouldIndexDocumentsFromDirectory() {
    // Given
    var directoryPath = "someDirectoryPath";
    var document = Document.builder()
        .id(randomUUID())
        .name("test.txt")
        .content("Sample content")
        .build();
    var indexedDocument = IndexedDocument.builder()
        .id(randomUUID())
        .fileName("test.txt")
        .wordFrequency(null)
        .build();

    when(fileSystemReader.readDocumentsFromDirectory(directoryPath)).thenReturn(List.of(document));
    when(documentIndexer.index(document)).thenReturn(indexedDocument);

    // When
    var indexedDocumentNames = documentIndexingService.indexDocumentsFromDirectory(directoryPath);

    // Then
    assertThat(indexedDocumentNames).containsExactly("test.txt");
    verify(documentRepository).clear();
    verify(documentRepository).add(indexedDocument);
    verifyNoMoreInteractions(documentRepository);
  }
}
