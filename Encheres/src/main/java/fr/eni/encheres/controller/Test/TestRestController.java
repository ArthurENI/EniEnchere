package fr.eni.encheres.controller.Test;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.ArticleService;
import fr.eni.encheres.services.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestRestController {

    private final ArticleService articleService;
    private final CategorieService categorieService;


    public TestRestController(ArticleService articleService, CategorieService categorieService) {
        this.articleService = articleService;
        this.categorieService = categorieService;
    }

    @GetMapping("/articles")
    public List<Article> afficherArticles() {
        return articleService.selectAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.selectArticleById(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/utilisateurs/{id}")
    public List<ResponseEntity<Article>> getArticleByUtilisateurId(@PathVariable Long id) {
        List<Article> articles = articleService.selectArticleByUtilisateur(id);
        if(articles != null){
            return articles.stream().map(ResponseEntity::ok).toList();
        }else{
            return List.of(ResponseEntity.notFound().build());
        }
    }

    @GetMapping("/categories")
    public List<Categorie> afficherCategories() {
        return categorieService.selectAllCategories();
    }


}
