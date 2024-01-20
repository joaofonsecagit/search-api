package com.app.documentapi.domain.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SearchResult(
    UUID documentId,
    double rankScore
) {
}
