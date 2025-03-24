package com.example.demo.controller;

import com.example.demo.model.University;
import com.example.demo.service.UniversityService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @GetMapping
  public List<University> getAllUniversities() {
    return universityService.getAllUniversity();
  }
}