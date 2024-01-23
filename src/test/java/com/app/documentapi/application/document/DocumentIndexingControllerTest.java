package com.app.documentapi.application.document;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class DocumentIndexingControllerTest {

  @Mock
  private DocumentIndexingService documentIndexingService;
  @InjectMocks
  private DocumentIndexingController controller;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = standaloneSetup(controller).build();
  }

  @Test
  void shouldIndexDocuments() throws Exception {
    var directoryPath = "testDirectory";
    var requestBody = "{\"directoryPath\":\"" + directoryPath + "\"}";

    mockMvc.perform(post("/index")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.directoryPath").value(directoryPath));

    verify(documentIndexingService).indexDocumentsFromDirectory(directoryPath);
  }
}
