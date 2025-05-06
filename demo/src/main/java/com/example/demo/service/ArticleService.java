package com.example.demo.service;

import com.example.demo.model.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final Map<Long, Article> articles = new HashMap<>();

    public Article getArticleById(Long id) {
        return articles.getOrDefault(id, null);
    }

    public List<Article> getAllArticles() {
        return new ArrayList<>(articles.values());
    }

    public Article createArticle(Article article) {
        Long id = (long) (articles.size() + 1);
        article.setId(id);
        articles.put(id, article);
        return article;
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        if (articles.containsKey(id)) {
            updatedArticle.setId(id);
            articles.put(id, updatedArticle);
            return updatedArticle;
        }
        return null;
    }

    public void partiallyUpdateArticle(Long id, Map<String, Object> updates) {
        if (articles.containsKey(id)) {
            Article article = articles.get(id);
            updates.forEach((key, value) -> {
                if ("title".equals(key)) {
                    article.setTitle((String) value);
                } else if ("content".equals(key)) {
                    article.setContent((String) value);
                }
            });
            articles.put(id, article);
        }
    }

    public void deleteArticle(Long id) {
      articles.remove(id);
    }
}