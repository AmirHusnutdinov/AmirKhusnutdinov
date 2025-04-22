package com.example.demo.controller;

import com.example.demo.model.TrainingCourses;
import com.example.demo.service.TrainingCoursesService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/training-courses")
public class TrainingCoursesController {

  private final TrainingCoursesService trainingCoursesService;

  public TrainingCoursesController(TrainingCoursesService trainingCoursesService) {
    this.trainingCoursesService = trainingCoursesService;
  }

  @GetMapping
  public List<TrainingCourses> getAllTrainingCourses() {
    return trainingCoursesService.getAllTrainingCourses();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TrainingCourses> getTrainingCourseById(@PathVariable Long id) {
    return trainingCoursesService.getTrainingCourseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<TrainingCourses> createTrainingCourse(
      @RequestBody TrainingCourses trainingCourse) {
    TrainingCourses createdCourse = trainingCoursesService.createTrainingCourse(trainingCourse);
    return ResponseEntity.status(201).body(createdCourse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TrainingCourses> updateTrainingCourse(@PathVariable Long id,
      @RequestBody TrainingCourses updatedCourse) {
    TrainingCourses course = trainingCoursesService.updateTrainingCourse(id, updatedCourse);
    return ResponseEntity.ok(course);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTrainingCourse(@PathVariable Long id) {
    trainingCoursesService.deleteTrainingCourse(id);
    return ResponseEntity.noContent().build();
  }
}
