package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ArticleControllerE2ETest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testGetArticleById() {
    // Assuming an article with ID 1 exists
    String url = "http://localhost:" + port + "/api/articles/1";
    ResponseEntity<Article> response = restTemplate.getForEntity(url, Article.class);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Test Article", response.getBody().getTitle()); // Adjust based on your test data
  }

  @Test
  void testCreateArticle() {
    Article newArticle = new Article();
    newArticle.setTitle("New Article");
    newArticle.setContent("This is a new article.");

    ResponseEntity<Article> response = restTemplate.postForEntity(
        "http://localhost:" + port + "/api/articles", newArticle, Article.class);
    assertEquals(201, response.getStatusCodeValue());
    assertEquals("New Article", response.getBody().getTitle());
  }

  @Test
  void testUpdateArticle() {
    // Assuming an article with ID 1 exists
    Article updatedArticle = new Article();
    updatedArticle.setTitle("Updated Article");
    updatedArticle.setContent("This is an updated article.");

    restTemplate.put("http://localhost:" + port + "/api/articles/1", updatedArticle);

    ResponseEntity<Article> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/api/articles/1", Article.class);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Updated Article", response.getBody().getTitle());
  }

  @Test
  void testDeleteArticle() {
    // Assuming an article with ID 1 exists
    restTemplate.delete("http://localhost:" + port + "/api/articles/1");

    ResponseEntity<Article> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/api/articles/1", Article.class);
    assertEquals(404, response.getStatusCodeValue());
  }
}
