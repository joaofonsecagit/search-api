package com.app.documentapi.domain.model;

import java.util.UUID;
import lombok.Builder;

@Builder
public record SearchResult(
    UUID documentId,
    double rankScore
) {
}
