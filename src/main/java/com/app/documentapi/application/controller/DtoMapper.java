package com.app.documentapi.application.controller;

import com.app.documentapi.application.document.IndexResponseDto;
import com.app.documentapi.application.search.SearchDto;
import com.app.documentapi.application.search.SearchResponseDto;
import com.app.documentapi.domain.model.SearchResult;
import java.util.ArrayList;
import java.util.List;


public interface DtoMapper {
  static IndexResponseDto toDto(String directoryPath, List<String> documents) {
    return IndexResponseDto.builder()
        .directoryPath(directoryPath)
        .indexedDocuments(documents)
        .build();

  }
  static SearchResponseDto toDto(List<SearchResult> searchResult) {
    var results = new ArrayList<SearchDto>();

    searchResult.forEach(
        result -> results.add(SearchDto.builder()
            .fileName(result.fileName())
            .rankScore(result.rankScore())
            .build())
    );

    return SearchResponseDto.builder()
        .searchResults(results)
        .build();
  }
}
