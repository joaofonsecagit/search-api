package com.app.documentapi.domain.services;

import static java.util.Comparator.comparingDouble;

import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.model.SearchResult;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentSearcher {

  private final RankingService rankingService;
  public List<SearchResult> search(String query, List<IndexedDocument> indexedDocuments) {
    log.info("Searching query '{}' in {} indexed documents", query, indexedDocuments.size());

    var queryWords = query.toLowerCase().split("\\W+");

    return indexedDocuments.stream()
        .map(indexedDoc -> createSearchResult(indexedDoc, queryWords))
        .filter(Objects::nonNull)
        .sorted(comparingDouble(SearchResult::rankScore).reversed())
        .limit(10) //TODO set configuration in app yml
        .toList();
  }

  private SearchResult createSearchResult(IndexedDocument indexedDoc, String[] queryWords) {
    double rankScore = rankingService.calculateRankScore(queryWords, indexedDoc.wordFrequency());
    if (rankScore > 0) {
      return SearchResult.builder()
          .fileId(indexedDoc.id())
          .fileName(indexedDoc.fileName())
          .rankScore(rankScore)
          .build();
    }
    return null;
  }
}