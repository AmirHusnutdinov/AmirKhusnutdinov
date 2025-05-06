package com.example.demo.controller;

import com.example.demo.model.University;
import com.example.demo.service.UniversityService;
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
@RequestMapping("/api/universities")
public class UniversityController {

  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @GetMapping
  public List<University> getAllUniversities() {
    return universityService.getAllUniversities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
    return universityService.getUniversityById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<University> createUniversity(@RequestBody University university) {
    University createdUniversity = universityService.createUniversity(university);
    return ResponseEntity.status(201).body(createdUniversity);
  }

  @PutMapping("/{id}")
  public ResponseEntity<University> updateUniversity(@PathVariable Long id,
      @RequestBody University updatedUniversity) {
    University university = universityService.updateUniversity(id, updatedUniversity);
    return ResponseEntity.ok(university);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
    universityService.deleteUniversity(id);
    return ResponseEntity.noContent().build();
  }
}
