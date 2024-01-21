package com.app.documentapi.application.search;

import com.app.documentapi.domain.DocumentRepository;
import com.app.documentapi.domain.model.SearchResult;
import com.app.documentapi.domain.services.DocumentSearcher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<SearchResult>> search(@RequestParam String query) {
    // Retrieve the indexed documents from the repository
    var all = repository.getAll();
    log.info("searching: {}", all);
    List<SearchResult> results = documentSearcher.search(query, repository.getAll());

    // Return the results
    return ResponseEntity.ok(results);
  }
}