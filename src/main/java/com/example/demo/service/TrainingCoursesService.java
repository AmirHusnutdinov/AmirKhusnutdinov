package com.example.demo.service;

import com.example.demo.model.TrainingCourses;
import com.example.demo.repository.TrainingCoursesRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TrainingCoursesService {

  private static final Logger logger = LoggerFactory.getLogger(TrainingCoursesService.class);
  private final TrainingCoursesRepository trainingCoursesRepository;

  public TrainingCoursesService(TrainingCoursesRepository trainingCoursesRepository) {
    this.trainingCoursesRepository = trainingCoursesRepository;
  }

  public List<TrainingCourses> getAllTrainingCourses() {
    logger.info("Fetching all Training Courses from the database");
    return trainingCoursesRepository.findAll();
  }
}
