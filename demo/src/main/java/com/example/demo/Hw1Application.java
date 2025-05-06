package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.model.TrainingCourses;
import com.example.demo.model.University;
import com.example.demo.model.AppUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TrainingCoursesRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Hw1Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw1Application.class, args);
  }

  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository, BookRepository bookRepository,
      UniversityRepository universityRepository, TrainingCoursesRepository courseRepository) {
    return args -> {
      userRepository.save(new AppUser(null, "John Doe", "john@example.com"));
      bookRepository.save(new Book(null, "Spring in Action", "Craig Walls"));
      universityRepository.save(new University(null, "MIPT", "aboba"));
      courseRepository.save(new TrainingCourses(null, "Java Programming", "1", "2"));
    };
  }
}
