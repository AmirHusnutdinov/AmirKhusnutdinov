package com.example.demo.service;

import com.example.demo.model.TrainingCourses;
import com.example.demo.repository.TrainingCoursesRepository;
import java.util.List;
import java.util.Optional;
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
    logger.info("Fetching all training courses from the database");
    return trainingCoursesRepository.findAll();
  }

  public Optional<TrainingCourses> getTrainingCourseById(Long id) {
    logger.info("Fetching training course with ID: {}", id);
    return trainingCoursesRepository.findById(id);
  }

  public TrainingCourses createTrainingCourse(TrainingCourses trainingCourse) {
    logger.info("Creating a new training course: {}", trainingCourse);
    return trainingCoursesRepository.save(trainingCourse);
  }

  public TrainingCourses updateTrainingCourse(Long id, TrainingCourses updatedCourse) {
    logger.info("Updating training course with ID: {}", id);
    updatedCourse.setId(id);
    return trainingCoursesRepository.save(updatedCourse);
  }

  public void deleteTrainingCourse(Long id) {
    logger.info("Deleting training course with ID: {}", id);
    trainingCoursesRepository.deleteById(id);
  }

  public void partiallyUpdateTrainingCourse(Long id, TrainingCourses updatedCourse) {
    logger.info("Partially updating training course with ID: {}", id);
    Optional<TrainingCourses> existingCourseOpt = trainingCoursesRepository.findById(id);
    if (existingCourseOpt.isPresent()) {
      TrainingCourses existingCourse = existingCourseOpt.get();
      if (updatedCourse.getName() != null) {
        existingCourse.setName(updatedCourse.getName());
      }
      if (updatedCourse.getDescription() != null) {
        existingCourse.setDescription(updatedCourse.getDescription());
      }
      if (updatedCourse.getTeacher() != null) {
        existingCourse.setTeacher(updatedCourse.getTeacher());
      }
      trainingCoursesRepository.save(existingCourse);
    }
  }
}
