package com.example.demo.controller;

import com.example.demo.api.ArticleApi;
import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController implements ArticleApi {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;

  }

  @Override
  public ResponseEntity<Article> getArticleById(Long id) {
    Article article = articleService.getArticleById(id);
    return ResponseEntity.ok(article);
  }

  @Override
  public ResponseEntity<List<Article>> getAllArticles() {
    List<Article> articles = articleService.getAllArticles();
    return ResponseEntity.ok(articles);
  }

  @Override
  public ResponseEntity<Article> createArticle(Article article) {
    Article createdArticle = articleService.createArticle(article);
    return ResponseEntity.status(201).body(createdArticle);
  }

  @Override
  public ResponseEntity<Article> updateArticle(Long id, Article updatedArticle) {
    Article article = articleService.updateArticle(id, updatedArticle);
    return ResponseEntity.ok(article);
  }

  @Override
  public ResponseEntity<Void> partiallyUpdateArticle(Long id, Map<String, Object> updates) {
    articleService.partiallyUpdateArticle(id, updates);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> deleteArticle(Long id) {
    articleService.deleteArticle(id);
    return ResponseEntity.noContent().build();
  }
}
