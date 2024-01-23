package com.app.documentapi.application.search;

import com.app.documentapi.domain.model.SearchResult;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchMapper {

  public static SearchResponseDto toDto(List<SearchResult> searchResult) {
    var response = SearchResponseDto.builder().build();

    searchResult.forEach(
        result -> response.searchResults()
            .add(SearchDto.builder()
                .fileName(result.fileName())
                .rankScore(result.rankScore())
                .build())
    );

    return response;
  }
}
