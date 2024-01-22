package com.app.documentapi.application.controller;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ApiError {
  String code;
  String status;
  String message;
}
