package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.services.ArticleService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    ArticleService articleService;


    @GetMapping()
    public String afficherArticles(Model model){
        List<Article> lstArticles = articleService.selectAllArticles();
        model.addAttribute("services", lstArticles);
        return "articles/articles-page";
    }

    @GetMapping("/detail")
    public String afficherUnArticle(
            @RequestParam(name = "id", required = true) Long id, Model model ) {
        if(id>0){
            Article article =articleService.selectArticleById(id);
            if(article!=null){
                model.addAttribute("article", article);
                return "articles/article-detail";
            }
        }
        return "redirect:/articles";
    }

    @PostMapping("/chercher")
    public String rechercherArticle(
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "categorie", required = false) Long categorieId,
            Model model) {
        List<Article> articlesFiltres = articleService.filterArticles(nom, categorieId);
        model.addAttribute("articles", articlesFiltres);
        return "articles/articles-page";
    }
}
