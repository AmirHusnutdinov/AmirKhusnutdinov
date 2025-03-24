package com.example.demo.service;

import com.example.demo.model.University;
import com.example.demo.repository.UniversityRepository;
import java.util.List;
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

  public List<University> getAllUniversity() {
    logger.info("Fetching all universitas from the database");
    return universityRepository.findAll();
  }
}
