package com.app.documentapi.application.document;

import lombok.Builder;

@Builder
public record IndexDto(
    String directoryPath
) {
}
