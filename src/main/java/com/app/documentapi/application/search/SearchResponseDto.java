package com.app.documentapi.application.search;

import java.util.List;
import lombok.Builder;

@Builder
public record SearchResponseDto(
    List<SearchDto> searchResults
) {
}
