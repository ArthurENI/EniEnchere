package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.ArticleService;
import fr.eni.encheres.services.EnchereService;
import fr.eni.encheres.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
@RequestMapping("/encheres")
public class EnchereController {
    EnchereService enchereService;
    ArticleService articleService;
    UtilisateurService utilisateurService;

    public EnchereController(EnchereService enchereService, ArticleService articleService, UtilisateurService utilisateurService) {
        this.enchereService = enchereService;
        this.articleService = articleService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/liste")
    public String afficherEncheres(Model model) {

        List<Enchere> encheres = enchereService.selectAllEncheres();
        model.addAttribute("encheres", encheres);

        return "encheres/encheres-page";
    }

    @GetMapping("/detail/{id}")
    public String detailEnchere(
            @PathVariable(name = "id") Long id,
            Model model) {
        if (id > 0) {
            Enchere enchere = enchereService.getLastEnchere(enchereService.selectEnchereById(id));

            if (enchere != null) {

                model.addAttribute("enchere", enchere);

                return "encheres/enchere-detail-page";
            }
        }
        return "redirect:/encheres";
    }

    @PostMapping("/ajout")
    public String ajoutEnchere(
            @RequestParam("articleId") Long articleId,
            @RequestParam("montant") int montant,
            @SessionAttribute("loggedUser") Utilisateur user,
            RedirectAttributes redirectAttributes
    ) {

        try {
            enchereService.placerEnchere(articleId, articleService.selectArticleById(articleId).getUtilisateur().getNoUtilisateur(), montant, user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/articles/detail/" + articleId;
        }
        EniFlashMessage.sendSuccessFlash(redirectAttributes, "Enchère placée avec succès !");
        return "redirect:/articles/detail/" + articleId;
    }


    @GetMapping("/test")
    public String afficherFormulaireTest(Model model) {
        return "test-enchere";
    }

}


