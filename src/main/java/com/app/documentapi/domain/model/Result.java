package com.app.documentapi.domain.model;

import lombok.Builder;

@Builder
public record Result(
        Document document,
        double rankScore
) {
}
