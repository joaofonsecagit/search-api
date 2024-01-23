package com.app.documentapi.domain.services;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.app.documentapi.domain.model.IndexedDocument;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentSearcherTest {

  @Mock
  private RankingService rankingService;

  @InjectMocks
  private DocumentSearcher documentSearcher;

  @Test
  void shouldReturnSearchResults() {
    // Given
    var query = "test";
    var docId = randomUUID();
    var wordFrequency = Map.of("test", 1);
    var indexedDocument = IndexedDocument.builder()
        .id(docId)
        .fileName("testDocument.txt")
        .wordFrequency(wordFrequency)
        .build();

    when(rankingService.calculateRankScore(new String[]{"test"}, wordFrequency)).thenReturn(1.0);

    // When
    var results = documentSearcher.search(query, List.of(indexedDocument));

    // Then
    assertThat(results).hasSize(1);
    assertThat(results.get(0).fileId()).isEqualTo(docId);
    assertThat(results.get(0).rankScore()).isEqualTo(1.0);
  }

  @Test
  void shouldReturnEmptyListWhenNoMatch() {
    // Given
    var query = "nomatch";
    var docId = randomUUID();
    var wordFrequency = Map.of("test", 1);
    var indexedDocument = IndexedDocument.builder()
        .id(docId)
        .fileName("testDocument.txt")
        .wordFrequency(wordFrequency)
        .build();

    when(rankingService.calculateRankScore(new String[]{"nomatch"}, wordFrequency)).thenReturn(0.0);

    // When
    var results = documentSearcher.search(query, List.of(indexedDocument));

    // Then
    assertThat(results).isEmpty();
  }

  @Test
  void shouldReturnSearchResultsForMultipleDocuments() {
    // Given
    var query = "test";
    var doc1 = IndexedDocument.builder()
        .id(randomUUID())
        .fileName("doc1.txt")
        .wordFrequency(Map.of("test", 1))
        .build();

    var doc2 = IndexedDocument.builder()
        .id(randomUUID())
        .fileName("doc2.txt")
        .wordFrequency(Map.of("test", 2))
        .build();

    when(rankingService.calculateRankScore(new String[]{"test"}, doc1.wordFrequency())).thenReturn(0.5);
    when(rankingService.calculateRankScore(new String[]{"test"}, doc2.wordFrequency())).thenReturn(1.0);

    // When
    var results = documentSearcher.search(query, List.of(doc1, doc2));

    // Then
    assertThat(results).hasSize(2);
    assertThat(results.get(0).rankScore()).isGreaterThan(results.get(1).rankScore());
  }
}
