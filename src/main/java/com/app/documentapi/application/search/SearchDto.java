package com.app.documentapi.application.search;

public record SearchDto(
    String fileName,
    double rankScore
) {
}
