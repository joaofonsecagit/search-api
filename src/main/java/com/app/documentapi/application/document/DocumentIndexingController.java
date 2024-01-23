package com.app.documentapi.application.document;

import static com.app.documentapi.application.document.IndexMapper.toDto;

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
  public IndexResponseDto indexDocuments(@RequestBody IndexResponseDto indexResponseDto) {
    var directoryPath = indexResponseDto.directoryPath();
    return toDto(directoryPath, documentIndexingService.indexDocumentsFromDirectory(directoryPath));
  }
}
