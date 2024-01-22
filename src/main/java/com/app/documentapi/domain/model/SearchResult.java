package com.app.documentapi.domain.model;

import java.util.UUID;
import lombok.Builder;

@Builder
public record SearchResult(
    UUID fileId,
    String fileName,
    double rankScore
) {
}
