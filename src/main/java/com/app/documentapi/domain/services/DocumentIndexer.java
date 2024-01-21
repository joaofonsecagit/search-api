package com.app.documentapi.domain.services;

import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentIndexer {

  public IndexedDocument index(Document document) {
    // Tokenize the document content
    String[] words = tokenize(document.content());

    // Count word occurrences
    Map<String, Integer> wordFrequency = countWordFrequency(words);

    // Create an IndexedDocument
    return IndexedDocument.builder()
        .id(UUID.randomUUID())
        .wordFrequency(wordFrequency)
        .build();
  }

  private String[] tokenize(String content) {
    // Simple tokenization by splitting on non-word characters
    return content.split("\\W+");
  }

  private Map<String, Integer> countWordFrequency(String[] words) {
    Map<String, Integer> frequency = new HashMap<>();
    for (String word : words) {
      word = word.toLowerCase(); // Normalize to lowercase
      frequency.put(word, frequency.getOrDefault(word, 0) + 1);
    }
    return frequency;
  }
}