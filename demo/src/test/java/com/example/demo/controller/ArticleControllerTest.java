package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class) // Используем Mockito для тестирования
public class ArticleControllerTest {

  private MockMvc mockMvc;

  @Mock // Создаем мок для ArticleService
  private ArticleService articleService;

  @InjectMocks // Внедряем мок в ArticleController
  private ArticleController articleController;

  @BeforeEach
  void setUp() {
    // Настройка MockMvc с использованием ArticleController
    mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
  }

  @Test
  void testGetArticleById() throws Exception {
    Article article = new Article(1L, "Test Article", "This is a test article.");
    when(articleService.getArticleById(1L)).thenReturn(article);

    mockMvc.perform(get("/api/articles/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Test Article"));
  }

  @Test
  void testGetAllArticles() throws Exception {
    when(articleService.getAllArticles()).thenReturn(
        Collections.singletonList(new Article(1L, "Test Article", "This is a test article.")));

    mockMvc.perform(get("/api/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].title").value("Test Article"));
  }

  @Test
  void testCreateArticle() throws Exception {
    Article article = new Article(1L, "New Article", "This is a new article.");
    when(articleService.createArticle(any(Article.class))).thenReturn(article);

    mockMvc.perform(post("/api/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"New Article\", \"content\":\"This is a new article.\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("New Article"));
  }

  @Test
  void testUpdateArticle() throws Exception {
    Article updatedArticle = new Article(1L, "Updated Article", "This is an updated article.");
    when(articleService.updateArticle(1L, any(Article.class))).thenReturn(updatedArticle);

    mockMvc.perform(put("/api/articles/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Updated Article\", \"content\":\"This is an updated article.\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("Updated Article"));
  }
}
