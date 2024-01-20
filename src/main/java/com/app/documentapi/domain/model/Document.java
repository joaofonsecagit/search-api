package com.app.documentapi.domain.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Document(
    UUID id,
    String name,
    String content
) {
}
