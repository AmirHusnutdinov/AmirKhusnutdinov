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

import com.example.demo.model.TrainingCourses;
import com.example.demo.service.TrainingCoursesService;
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

@WebMvcTest(TrainingCoursesController.class)
public class TrainingCoursesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private TrainingCoursesService trainingCoursesService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetTrainingCourseById() throws Exception {
    TrainingCourses course = new TrainingCourses(1L, "Test Course", "Description");
    when(trainingCoursesService.getTrainingCourseById(1L)).thenReturn(Optional.of(course));

    mockMvc.perform(get("/api/training-courses/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Test Course"));
  }

  @Test
  void testGetAllTrainingCourses() throws Exception {
    when(trainingCoursesService.getAllTrainingCourses()).thenReturn(
        Collections.singletonList(new TrainingCourses(1L, "Test Course", "Description")));

    mockMvc.perform(get("/api/training-courses"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].title").value("Test Course"));
  }

  @Test
  void testCreateTrainingCourse() throws Exception {
    TrainingCourses course = new TrainingCourses(1L, "New Course", "New Description");
    when(trainingCoursesService.createTrainingCourse(any(TrainingCourses.class))).thenReturn(
        course);

    mockMvc.perform(post("/api/training-courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"New Course\", \"description\":\"New Description\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("New Course"));
  }

  @Test
  void testUpdateTrainingCourse() throws Exception {
    TrainingCourses updatedCourse = new TrainingCourses(1L, "Updated Course",
        "Updated Description");
    when(trainingCoursesService.updateTrainingCourse(1L, any(TrainingCourses.class))).thenReturn(
        updatedCourse);

    mockMvc.perform(put("/api/training-courses/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Updated Course\", \"description\":\"Updated Description\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Updated Course"));
  }

  @Test
  void testDeleteTrainingCourse() throws Exception {
    doNothing().when(trainingCoursesService).deleteTrainingCourse(1L);

    mockMvc.perform(delete("/api/training-courses/1"))
        .andExpect(status().isNoContent());
  }
}
