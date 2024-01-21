package com.app.documentapi.domain.file;

import com.app.documentapi.domain.model.Document;
import java.io.IOException;
import java.nio.file.Path;

public interface FileReadingStrategy {
  Document readDocument(Path filePath) throws IOException;
}
