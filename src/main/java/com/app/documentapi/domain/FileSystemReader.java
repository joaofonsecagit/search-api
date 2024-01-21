package com.app.documentapi.domain;

import com.app.documentapi.domain.model.Document;
import java.util.List;

public interface FileSystemReader {

  List<Document> readDocumentsFromDirectory(String directoryPath);
}
