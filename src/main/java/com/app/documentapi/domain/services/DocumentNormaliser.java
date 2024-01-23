package com.app.documentapi.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentNormaliser {

  public String[] tokenize(String documentContent) {
    var normalizedContent = normalizeContent(documentContent);
    return normalizedContent.split("\\W+");
  }

  private String normalizeContent(String content) {
    return content.toLowerCase().replaceAll("[^a-z0-9\\s]", " ");
  }
}
