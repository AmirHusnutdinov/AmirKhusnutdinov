package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TrainingCourses {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String teacher;

  public TrainingCourses() {
  }

  public TrainingCourses(Long id, String name, String description, String teacher) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.teacher = teacher;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
  public String getTeacher() {
    return teacher;
  }
}
