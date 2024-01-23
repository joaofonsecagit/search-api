package com.app.documentapi.domain.model;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;

@Builder
public record IndexedDocument(
    UUID id,
    String fileName,
    Map<String, Integer> wordFrequency
) {
}
