package com.app.documentapi.infrastructure.adapters;

import static java.nio.file.Files.isRegularFile;
import static java.nio.file.Files.walk;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.app.documentapi.domain.file.FileReadingStrategy;
import com.app.documentapi.domain.model.Document;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileSystemAdapterTest {

  @Mock
  private FileReadingStrategy textFileReadingStrategy;
  private FileSystemAdapter fileSystemAdapter;

  @BeforeEach
  void setUp() {
    fileSystemAdapter = new FileSystemAdapter();
    fileSystemAdapter.strategies.put("txt", textFileReadingStrategy);

    var mockedFiles = mockStatic(Files.class);
    mockedFiles.when(() -> walk(any(Path.class)))
        .thenReturn(Stream.of(Path.of("test.txt")));
    mockedFiles.when(() -> isRegularFile(any(Path.class))).thenReturn(true);
  }

  @Test
  void shouldReadDocumentsFromDirectory() {
    var mockDocument = Document.builder()
        .id(randomUUID())
        .name("test.txt")
        .content("Test content")
        .build();
    when(textFileReadingStrategy.readDocument(any(Path.class))).thenReturn(mockDocument);

    var documents = fileSystemAdapter.readDocumentsFromDirectory("testDirectory");

    assertThat(documents).hasSize(1);
    assertThat(documents.get(0).name()).isEqualTo("test.txt");
    assertThat(documents.get(0).content()).isEqualTo("Test content");

    verify(textFileReadingStrategy).readDocument(Path.of("test.txt"));
  }
}
