package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private BookService bookService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetBookById() throws Exception {
    Book book = new Book(1L, "Test Book", "Author Name");
    when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

    mockMvc.perform(get("/api/books/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Test Book"));
  }

  @Test
  void testGetAllBooks() throws Exception {
    when(bookService.getAllBooks()).thenReturn(
        Collections.singletonList(new Book(1L, "Test Book", "Author Name")));

    mockMvc.perform(get("/api/books"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].title").value("Test Book"));
  }

  @Test
  void testCreateBook() throws Exception {
    Book book = new Book(1L, "New Book", "New Author");
    when(bookService.createBook(any(Book.class))).thenReturn(book);

    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"New Book\", \"author\":\"New Author\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("New Book"));
  }

  @Test
  void testUpdateBook() throws Exception {
    Book updatedBook = new Book(1L, "Updated Book", "Updated Author");
    when(bookService.updateBook(1L, any(Book.class))).thenReturn(updatedBook);

    mockMvc.perform(put("/api/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Updated Book\", \"author\":\"Updated Author\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Updated Book"));
  }

  @Test
  void testDeleteBook() throws Exception {
    doNothing().when(bookService).deleteBook(1L);

    mockMvc.perform(delete("/api/books/1"))
        .andExpect(status().isNoContent());
  }
}