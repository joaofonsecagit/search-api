package com.app.documentapi.application.search;

import lombok.Builder;

@Builder
public record SearchDto(
    String fileName,
    double rankScore
) {
}
