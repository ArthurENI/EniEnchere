package fr.eni.encheres.controller;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.exception.BusinessException;
import fr.eni.encheres.services.AdresseService;
import fr.eni.encheres.services.ArticleService;

import fr.eni.encheres.services.CategorieService;
import fr.eni.encheres.services.EnchereService;
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
    private final EnchereService enchereService;

    public ArticleController(ArticleService articleService, CategorieService categorieService
    , EnchereService enchereService) {
        this.articleService = articleService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
    }


    @GetMapping("/detail/{id}")
    public String afficherUnArticle(
            @PathVariable Long id, Model model ) {
        int montantMinimum = articleService.selectArticleById(id).getMiseAPrix();
        if(id>0){
            Article article =articleService.selectArticleById(id);
            Enchere enchere = enchereService.getLastEnchere(enchereService.selectMeilleureEnchere(id));
            if(enchere != null){
                montantMinimum = enchereService.getLastEnchere(enchereService.selectMeilleureEnchere(id)).getMontant_enchere() + 1;
            }


            if(article!=null){
                model.addAttribute("article", article);
                if(enchere != null){
                    model.addAttribute("enchere", enchere);
                }
                model.addAttribute("encherir", montantMinimum);
                return "encheres/enchere-detail-page";
            }
        }
        return "redirect:/articles";
    }



    @GetMapping("/encheres")
    public String afficherEncheres(Model model){
        model.addAttribute("articles", articleService.selectAllArticles());
        model.addAttribute("categorie", categorieService.selectAllCategories());
        return "encheres/ListVentes-page";
    }

    @PostMapping("/encheres")
    public String rechercherArticle(
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "categorie", required = false) Long categorieId,
            @RequestParam(name = "statut", required = false) String statut,
            Model model) {
        List<Article> articles = articleService.selectAllArticles();
        model.addAttribute("categorie", categorieService.selectAllCategories());

        if(nom!=null){
            articles = articles.stream().filter(a -> a.getNomArticle().toLowerCase().contains(nom.toLowerCase()))
                    .toList();
        }
        if(categorieId != null){
            articles = articles.stream().filter(a -> a.getCategorie().getNoCategorie().equals(categorieId))
                    .toList();
        }
        if(statut != null){
            switch (statut) {
                case "ouvertes" -> articles = articles.stream()
                        .filter(a -> a.getEtatVente() == EtatVente.OUVERTE)
                        .toList();
                case "nonDebutees" -> articles = articles.stream()
                        .filter(a -> a.getEtatVente() == EtatVente.ATTENTE)
                        .toList();
                case "terminees" -> articles = articles.stream()
                        .filter(a -> a.getEtatVente() == EtatVente.TERMINEE)
                        .toList();
            }
        }
        model.addAttribute("articles", articles);

        return "encheres/ListVentes-page";
    }

    @GetMapping("/vente")
    public String creerVente(Model model, @SessionAttribute("loggedUser") Utilisateur utilisateur)
    {
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

    @PostMapping("/utilisateurs")
    public String afficherArticlesParUtilisateur(
            @SessionAttribute("loggedUser") Utilisateur utilisateur,
            Model model){

        List<Article> articles = articleService.selectArticleByUtilisateur(utilisateur.getNoUtilisateur());

        model.addAttribute("articles", articles);
        return "encheres/ListVentes-page";
    }
}
