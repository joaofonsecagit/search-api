package com.app.documentapi.domain.model;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;

@Builder
public record IndexedDocument(
    UUID id,
    Map<String, Integer> wordFrequency
) {
}
