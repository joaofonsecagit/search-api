package com.app.documentapi.application.document;

import java.util.List;
import lombok.Builder;

@Builder
public record IndexDto(
    String directoryPath,
    List<String> indexedDocuments
) {
}
