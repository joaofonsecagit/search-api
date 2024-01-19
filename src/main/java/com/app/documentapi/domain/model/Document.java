package com.app.documentapi.domain.model;

import lombok.Builder;

@Builder
public record Document(
        String name,
        String content
) {
}
