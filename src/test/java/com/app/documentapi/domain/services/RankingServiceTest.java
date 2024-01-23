package com.app.documentapi.domain.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankingServiceTest {

  private final RankingService rankingService = new RankingService();

  static Stream<Arguments> provideRankingData() {
    return Stream.of(
        Arguments.of(new String[]{"test"}, Map.of("test", 1), 1.0),
        Arguments.of(new String[]{"missing"}, Map.of("test", 1), 0.0),
        Arguments.of(new String[]{"test", "another"}, Map.of("test", 1), 0.5),
        Arguments.of(new String[]{"test", "another"}, Map.of("test", 1, "another", 1), 1.0)
    );
  }

  @ParameterizedTest
  @MethodSource("provideRankingData")
  void shouldCalculateRankScoreCorrectly(String[] queryWords, Map<String, Integer> wordFrequency, double expectedScore) {
    double score = rankingService.calculateRankScore(queryWords, wordFrequency);
    assertThat(score).isEqualTo(expectedScore);
  }
}
