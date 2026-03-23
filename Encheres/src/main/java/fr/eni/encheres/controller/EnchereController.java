package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.services.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/encheres")
public class EnchereController {
    EnchereService enchereService;

    public EnchereController(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

    @GetMapping("/liste")
    public String afficherEncheres(Model model) {

        List<Enchere> encheres = enchereService.selectAllEncheres();
        model.addAttribute("encheres", encheres);

        return "encheres/encheres-page";
    }

    @GetMapping("/details")
    public String detailEnchere(
            @RequestParam(name = "id", required = true) Long id,
            Model model) {
        if (id > 0) {
            Enchere enchere = enchereService.selectEnchereById(id);
            if (enchere != null) {
                model.addAttribute("enchere", enchere);
                return "encheres/enchere-detail-page";
            }
        }
        return "redirect:/encheres";
    }

    @PostMapping("/ajout")
    public String ajoutEnchere(
            @RequestParam(name = "articleId", required = true) Long param1,
            @RequestParam(name = "utilisateurId", required = true) Long param2,
            @RequestParam(name = "montant", required = true) int param3,
            Model model) {

        enchereService.placerEnchere(param1, param2, param3);
        return "redirect:/articles/detail?id=" + param1;


    }
}


