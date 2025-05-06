package com.example.demo.service;

import com.example.demo.model.University;
import com.example.demo.repository.UniversityRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

  private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);
  private final UniversityRepository universityRepository;

  public UniversityService(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  public List<University> getAllUniversities() {
    logger.info("Fetching all universities from the database");
    return universityRepository.findAll();
  }

  public Optional<University> getUniversityById(Long id) {
    logger.info("Fetching university with ID: {}", id);
    return universityRepository.findById(id);
  }

  public University createUniversity(University university) {
    logger.info("Creating a new university: {}", university);
    return universityRepository.save(university);
  }

  public University updateUniversity(Long id, University updatedUniversity) {
    logger.info("Updating university with ID: {}", id);
    updatedUniversity.setId(id);
    return universityRepository.save(updatedUniversity);
  }

  public void deleteUniversity(Long id) {
    logger.info("Deleting university with ID: {}", id);
    universityRepository.deleteById(id);
  }

  public void partiallyUpdateUniversity(Long id, University updatedUniversity) {
    logger.info("Partially updating university with ID: {}", id);
    Optional<University> existingUniversityOpt = universityRepository.findById(id);
    if (existingUniversityOpt.isPresent()) {
      University existingUniversity = existingUniversityOpt.get();
      if (updatedUniversity.getName() != null) {
        existingUniversity.setName(updatedUniversity.getName());
      }
      if (updatedUniversity.getAddress() != null) {
        existingUniversity.setAddress(updatedUniversity.getAddress());
      }
      universityRepository.save(existingUniversity);
    }
  }
}