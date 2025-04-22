package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.University;
import com.example.demo.service.UniversityService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UniversityController.class)
public class UniversityControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UniversityService universityService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetUniversityById() throws Exception {
    University university = new University(1L, "Test University", "Description");
    when(universityService.getUniversityById(1L)).thenReturn(Optional.of(university));

    mockMvc.perform(get("/api/universities/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Test University"));
  }

  @Test
  void testGetAllUniversities() throws Exception {
    when(universityService.getAllUniversities()).thenReturn(
        Collections.singletonList(new University(1L, "Test University", "Description")));

    mockMvc.perform(get("/api/universities"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("Test University"));
  }

  @Test
  void testCreateUniversity() throws Exception {
    University university = new University(1L, "New University", "New Description");
    when(universityService.createUniversity(any(University.class))).thenReturn(university);

    mockMvc.perform(post("/api/universities")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"New University\", \"description\":\"New Description\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("New University"));
  }

  @Test
  void testUpdateUniversity() throws Exception {
    University updatedUniversity = new University(1L, "Updated University", "Updated Description");
    when(universityService.updateUniversity(1L, any(University.class))).thenReturn(
        updatedUniversity);

    mockMvc.perform(put("/api/universities/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Updated University\", \"description\":\"Updated Description\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Updated University"));
  }

  @Test
  void testDeleteUniversity() throws Exception {
    doNothing().when(universityService).deleteUniversity(1L);

    mockMvc.perform(delete("/api/universities/1"))
        .andExpect(status().isNoContent());
  }
}
