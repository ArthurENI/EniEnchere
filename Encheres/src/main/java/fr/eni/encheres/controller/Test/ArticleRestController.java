package fr.eni.encheres.controller.Test;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.services.ArticleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> afficherArticles() {
        return articleService.selectAllArticles();
    }
}
