package com.app.documentapi.application.search;


import static com.app.documentapi.application.controller.DtoMapper.toDto;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.services.DocumentSearcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

  private final DocumentSearcher documentSearcher;
  private final DocumentRepository repository;

  @GetMapping
  public SearchResponseDto search(@RequestParam String query) {
    return toDto(documentSearcher.search(query, repository.getAll()));
  }
}