package org.example.HTML;

import org.example.Article.Article;
import org.example.Article.ArticleService;
import org.example.Comment.CommentService;
import org.example.Controller;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleFreemarkerController implements Controller {

  private final static Logger LOG = LoggerFactory.getLogger(ArticleFreemarkerController.class);

  private final Service service;
  private final ArticleService articleService;
  private final FreeMarkerEngine freeMarkerEngine;

  public ArticleFreemarkerController(Service service,
      ArticleService articleService,
      CommentService commentService,
      FreeMarkerEngine freeMarkerEngine) {
    this.service = service;
    this.articleService = articleService;
    this.freeMarkerEngine = freeMarkerEngine;
  }

  @Override
  public void initializeEndpoints() {
    getAllArticles();
  }

  private void getAllArticles() {
    service.get(
        "/",
        (Request request, Response response) -> {
          response.type("text/html; charset=utf-8");
          List<Article> articles = articleService.findAll();
          List<Map<String, String>> model =
              articles.stream()
                  .map(article -> Map.of("title", article.title(),
                      "number", article.comments() == null ? "0"
                          : Integer.toString(article.comments().size())))
                  .toList();

          Map<String, Object> modelMap = new HashMap<>();
          modelMap.put("articles", model);
          LOG.debug("Article displayed");
          response.status(200);
          return freeMarkerEngine.render(new ModelAndView(modelMap, "index.ftl"));
        }
    );
  }
}
