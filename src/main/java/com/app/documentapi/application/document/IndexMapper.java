package com.app.documentapi.application.document;

import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IndexMapper {

  public static IndexResponseDto toDto(String directoryPath, List<String> documents) {
    return IndexResponseDto.builder()
        .directoryPath(directoryPath)
        .indexedDocuments(documents)
        .build();

  }
}
