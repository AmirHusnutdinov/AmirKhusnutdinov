package org.example.Article;

import org.example.Comment.Comment;

import java.util.List;
import java.util.Set;

public record Article(ArticleId id, String title, Set<String> tags, List<Comment> comments) {

  public Article withTitle(String title) {
    return new Article(id, title, tags, comments);
  }

  public Article withTags(Set<String> tags) {
    return new Article(id, title, tags, comments);
  }

  public Article withComments(List<Comment> comments) {
    return new Article(id, title, tags, comments);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return id.equals(article.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "ID = " + id.toString() + ", " +
        "Title = " + title + ", " +
        "Tags = " + tags + ", " +
        "Comments = " + comments;
  }
}
