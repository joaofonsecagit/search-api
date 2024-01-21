package com.app.documentapi.domain.model;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;

@Builder
public record IndexedDocument(
    UUID id,
    Map<String, Integer> wordFrequency
) {
}
