package com.app.documentapi.application.search;

import com.app.documentapi.domain.model.SearchResult;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SearchMapper {

  public static SearchResponseDto toDto(List<SearchResult> searchResult) {
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
