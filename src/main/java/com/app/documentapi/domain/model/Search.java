package com.app.documentapi.domain.model;

import lombok.Builder;

@Builder
public record Search(
        String query
) {
}
