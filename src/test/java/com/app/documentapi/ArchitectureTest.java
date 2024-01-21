package com.app.documentapi;

import static com.app.documentapi.ArchitectureTest.HexagonalArchitectureLayers.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {

  @Test
  void hexagonalArchitectureShouldBeRespected() {
    // given
    var allClasses = new ClassFileImporter().importPackages("com.app.documentapi");

    var rule = layeredArchitecture()
        .consideringOnlyDependenciesInLayers()
        .layer(DOMAIN).definedBy("..%s..".formatted(DOMAIN))
        .layer(APPLICATION).definedBy("..%s..".formatted(APPLICATION))
        .layer(INFRASTRUCTURE).definedBy("..%s..".formatted(INFRASTRUCTURE))

        .whereLayer(DOMAIN).mayNotAccessAnyLayer()
        .whereLayer(APPLICATION).mayOnlyAccessLayers(DOMAIN)
        .whereLayer(INFRASTRUCTURE).mayOnlyAccessLayers(DOMAIN, APPLICATION);

    // expect
    rule.check(allClasses);
  }

  public static class HexagonalArchitectureLayers {
    public static final String APPLICATION = "application";
    public static final String DOMAIN = "domain";
    public static final String INFRASTRUCTURE = "infrastructure";
  }
}