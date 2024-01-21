package com.app.documentapi.domain.model;

import java.util.UUID;
import lombok.Builder;

@Builder
public record Document(
    UUID id,
    String name,
    String content
) {
}
