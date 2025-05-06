package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetUserById() throws Exception { // Исправлено имя метода
    AppUser user = new AppUser(1L, "John Doe", "john@example.com");
    when(userService.getUserById(1L)).thenReturn(Optional.of(user)); // Исправлено имя метода

    mockMvc.perform(get("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("John Doe"));
  }

  @Test
  void testGetAllUsers() throws Exception {
    when(userService.getAllUsers()).thenReturn(
        Collections.singletonList(new AppUser(1L, "John Doe", "john@example.com")));

    mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("John Doe"));
  }

  @Test
  void testCreateUser() throws Exception {
    AppUser user = new AppUser(1L, "Jane Doe", "jane@example.com");
    when(userService.createUser(any(AppUser.class))).thenReturn(user);

    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Jane Doe\", \"email\":\"jane@example.com\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Jane Doe"));
  }

  @Test
  void testUpdateUser() throws Exception {
    AppUser updatedUser = new AppUser(1L, "John Smith", "john.smith@example.com");
    when(userService.updateUser(1L, any(AppUser.class))).thenReturn(updatedUser);

    mockMvc.perform(put("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"John Smith\", \"email\":\"john.smith@example.com\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("John Smith"));
  }

  @Test
  public void testDeleteUser() {
    // Arrange
    Long userId = 1L;
    doNothing().when(userService).deleteUser(userId); // Используйте doNothing для методов void

    // Act
    userService.deleteUser(userId);

    // Assert
    Mockito.verify(userService, times(1)).deleteUser(userId);
  }
}

