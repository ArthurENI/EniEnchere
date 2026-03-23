package fr.eni.encheres.controller;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.exception.BusinessException;
import fr.eni.encheres.services.AdresseService;
import fr.eni.encheres.services.ArticleService;

import fr.eni.encheres.services.CategorieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final CategorieService categorieService;
    private final ArticleService articleService;
    private final AdresseService adresseService;

    public ArticleController(ArticleService articleService, CategorieService categorieService
    , AdresseService adresseService) {
        this.articleService = articleService;
        this.categorieService = categorieService;
        this.adresseService = adresseService;
    }

    /*@GetMapping()
    public String afficherArticles(Model model){
        List<Article> lstArticles = articleService.selectAllArticles();
        model.addAttribute("services", lstArticles);
        return "articles/articles-page";
    }*/

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

    @GetMapping("/encheres")
    public String afficherEncheres(Model model){
        model.addAttribute("articles", articleService.selectAllArticles());

        return "encheres/ListEnchere-page";
    }

    @PostMapping("/chercher")
    public String rechercherArticle(
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "categorie", required = false) Long categorieId,
            @RequestParam(name = "etat", required = false) String etat,
            Model model) {
        List<Article> articlesFiltres = articleService.filterArticles(nom, categorieId,etat );
        model.addAttribute("articles", articlesFiltres);
        return "articles/articles-page";
    }

    @GetMapping("/vente")
    public String creerVente(Model model, @SessionAttribute("loggedUser") Utilisateur utilisateur)
    {
        System.out.println("CREER VENTE");
        Article article = new Article();
        article.setAdresseRetrait(new Adresse());
        model.addAttribute("article", article);
        model.addAttribute("categorie", categorieService.selectAllCategories());
        model.addAttribute("utilisateur", utilisateur);
        article.getAdresseRetrait().setRue(utilisateur.getAdresse().getRue());
        article.getAdresseRetrait().setCodePostal(utilisateur.getAdresse().getCodePostal());
        article.getAdresseRetrait().setVille(utilisateur.getAdresse().getVille());
        return "encheres/creationArticle-page";
    }

    @PostMapping("/vente")
    public String creerVente(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult,@SessionAttribute("loggedUser") Utilisateur utilisateur
    ){

        if(bindingResult.hasErrors()) {
            return "encheres/creationArticle-page";
        }
        else{
            try {
                article.setUtilisateur(utilisateur);
                LocalDateTime date = LocalDateTime.now();

                if(date.isBefore(article.getDateDebutEnchere())){
                    article.setEtatVente(EtatVente.ATTENTE);
                }else if(date.isAfter(article.getDateDebutEnchere())
                        && date.isBefore(article.getDateFinEnchere())){
                    article.setEtatVente(EtatVente.OUVERTE);
                }

                articleService.createArticle(article);
                return "redirect:/articles/encheres";
            }catch (BusinessException e){
                e.getClefsExternalisations().forEach(code->bindingResult.rejectValue("article", null, code));
                return "encheres/creationArticle-page";
            }
        }
    }
}
