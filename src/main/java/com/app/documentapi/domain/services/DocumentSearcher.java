package com.app.documentapi.domain.services;

import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.model.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toList;

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
        .collect(toList());
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