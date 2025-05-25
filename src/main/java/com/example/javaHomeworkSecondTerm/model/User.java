package com.example.javaHomeworkSecondTerm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @NotNull(message = "Email не может быть пустым")
  @Email(message = "Неверный формат email")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotNull(message = "Имя не может быть пустым")
  @Size(min = 1, message = "Имя должно содержать хотя бы 1 символ")
  @Column(name = "name")
  private String name;

  @Size(min = 1, message = "Фамилия должна содержать хотя бы 1 символ")
  @Column(name = "surname")
  private String surname;

  @JsonManagedReference
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Course> courses;

  public User(String email, String name, String surname) {
    this.email = email;
    this.name = name;
    this.surname = surname;
  }
}
