package com.example.demo.controller;

import com.example.demo.model.TrainingCourses;
import com.example.demo.service.TrainingCoursesService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainingCourses")
public class TrainingCoursesController {

  private final TrainingCoursesService trainingCoursesService;

  public TrainingCoursesController(TrainingCoursesService trainingCoursesService) {
    this.trainingCoursesService = trainingCoursesService;
  }

  @GetMapping
  public List<TrainingCourses> getAllTrainingCourses() {
    return trainingCoursesService.getAllTrainingCourses();
  }
}
