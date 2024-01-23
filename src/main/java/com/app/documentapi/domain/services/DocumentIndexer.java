package com.app.documentapi.domain.services;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import com.app.documentapi.domain.model.Document;
import com.app.documentapi.domain.model.IndexedDocument;
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
    log.info("Tokenize the document content: {}", document.name());

    var words = tokenize(document.content());
    var wordFrequency = countWordFrequency(words);

    return IndexedDocument.builder()
        .id(UUID.randomUUID())
        .fileName(document.name())
        .wordFrequency(wordFrequency)
        .build();
  }

  private String[] tokenize(String content) {
    return content.split("\\W+");
  }

  private Map<String, Integer> countWordFrequency(String[] words) {
    return stream(words)
        .map(String::toLowerCase)
        .collect(groupingBy(identity(), reducing(0, e -> 1, Integer::sum)));
  }
}