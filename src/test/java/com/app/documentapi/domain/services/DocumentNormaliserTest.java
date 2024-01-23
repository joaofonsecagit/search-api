package com.app.documentapi.domain.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DocumentNormaliserTest {

  private final DocumentNormaliser documentNormaliser = new DocumentNormaliser();

  @ParameterizedTest
  @CsvSource({
      "Hello World!, hello, world",
      "Hello@World$, hello, world",
      "HeLLo WoRLd, hello, world",
      "abc123 456def, abc123, 456def"
  })
  void shouldTokenizeAndNormalizeString(String input, String expectedToken1, String expectedToken2) {
    // Given
    var tokens = documentNormaliser.tokenize(input);

    // Expect
    if (expectedToken1.isEmpty() && expectedToken2.isEmpty()) {
      assertThat(tokens).isEmpty();
    } else if (expectedToken2.isEmpty()) {
      assertThat(tokens).containsExactly(expectedToken1);
    } else {
      assertThat(tokens).containsExactly(expectedToken1, expectedToken2);
    }
  }
}
