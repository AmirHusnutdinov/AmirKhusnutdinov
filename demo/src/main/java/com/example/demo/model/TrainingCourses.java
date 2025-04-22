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

  public TrainingCourses(long l, String testCourse, String description) {
  }

  public TrainingCourses(Long id, String name, String description, String teacher) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.teacher = teacher;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTeacher(String teacher) {
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
