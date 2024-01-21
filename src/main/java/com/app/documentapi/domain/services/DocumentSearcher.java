package com.app.documentapi.domain.services;

import com.app.documentapi.domain.model.IndexedDocument;
import com.app.documentapi.domain.model.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentSearcher {

  public List<SearchResult> search(String query, List<IndexedDocument> indexedDocuments) {
    // Tokenize the query
    String[] queryWords = query.toLowerCase().split("\\W+");

    List<SearchResult> results = new ArrayList<>();

    for (IndexedDocument indexedDoc : indexedDocuments) {
      double rankScore = calculateRankScore(queryWords, indexedDoc.wordFrequency());
      if (rankScore > 0) {
        results.add(new SearchResult(indexedDoc.id(), rankScore));
      }
    }

    // Sort the results by rank score in descending order
    results.sort((r1, r2) -> Double.compare(r2.rankScore(), r1.rankScore()));

    return results;
  }

  private double calculateRankScore(String[] queryWords, Map<String, Integer> wordFrequency) {
    int totalWords = queryWords.length;
    int matchedWords = 0;

    for (String word : queryWords) {
      if (wordFrequency.containsKey(word)) {
        matchedWords++;
      }
    }

    // Calculate rank score based on the proportion of matched words
    return (double) matchedWords / totalWords;
  }
}