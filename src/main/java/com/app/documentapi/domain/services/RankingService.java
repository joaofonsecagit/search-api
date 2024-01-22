package com.app.documentapi.domain.services;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingService {

  public double calculateRankScore(String[] queryWords, Map<String, Integer> wordFrequency) {
    int totalWords = queryWords.length;
    int matchedWords = 0;

    for (String word : queryWords) {
      if (wordFrequency.containsKey(word)) {
        matchedWords++;
      }
    }

    return (double) matchedWords / totalWords;
  }
}
