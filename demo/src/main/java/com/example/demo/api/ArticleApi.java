package com.example.demo.api;

import com.example.demo.model.Article;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Article", description = "Article management APIs")
@RequestMapping("/api/articles")
public interface ArticleApi {

  @Operation(summary = "Get article by ID", description = "Returns an article by its ID")
  @ApiResponse(responseCode = "200", description = "Successful operation")
  @ApiResponse(responseCode = "404", description = "Article not found")
  @GetMapping("/{id}")
  ResponseEntity<?> getArticleById(
      @Parameter(description = "ID of the article to retrieve") @PathVariable Long id);

  @Operation(summary = "Get all articles", description = "Returns a list of all articles")
  @ApiResponse(responseCode = "200", description = "Successful operation")
  @GetMapping
  ResponseEntity<?> getAllArticles();

  @Operation(summary = "Create a new article", description = "Creates a new article")
  @ApiResponse(responseCode = "201", description = "Article created")
  @PostMapping
  ResponseEntity<?> createArticle(@RequestBody Article article);

  @Operation(summary = "Update an article", description = "Updates an existing article")
  @ApiResponse(responseCode = "200", description = "Successful operation")
  @ApiResponse(responseCode = "404", description = "Article not found")
  @PutMapping("/{id}")
  ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle);

  @Operation(summary = "Partially update an article", description = "Partially updates an existing article")
  @ApiResponse(responseCode = "200", description = "Successful operation")
  @ApiResponse(responseCode = "404", description = "Article not found")
  @PatchMapping("/{id}")
  ResponseEntity<?> partiallyUpdateArticle(@PathVariable Long id,
      @RequestBody Map<String, Object> updates);

  @Operation(summary = "Delete an article", description = "Deletes an article by its ID")
  @ApiResponse(responseCode = "204", description = "Article deleted")
  @ApiResponse(responseCode = "404", description = "Article not found")
  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteArticle(@PathVariable Long id);
}
