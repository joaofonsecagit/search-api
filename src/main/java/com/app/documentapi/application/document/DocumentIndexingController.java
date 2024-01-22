package com.app.documentapi.application.document;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/index")
public class DocumentIndexingController {

  private final DocumentIndexingService documentIndexingService;

  @PostMapping
  public IndexDto indexDocuments(@RequestBody IndexDto indexDto) {
    return documentIndexingService.indexDocumentsFromDirectory(indexDto.directoryPath());
  }
}
