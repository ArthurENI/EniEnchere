package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.CategorieService;
import fr.eni.encheres.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
public class AdminController {

    private final UtilisateurService authService;
    private final CategorieService categorieService;

    public AdminController(UtilisateurService authService, CategorieService categorieService) {
        this.authService = authService;
        this.categorieService = categorieService;
    }

    @GetMapping("/encheres/admin/ListUtilisateur")
    public String showListUtilisateur(Model model) {
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }

        List<Utilisateur> utilisateurList = authService.getAllUtilisateur();

        model.addAttribute("utilisateurs", utilisateurList);

        return "admin/adminList-page";
    }


    @GetMapping("/encheres/admin/suppr/{id}")
    public String supprAdminProcces(@PathVariable(name = "id") Long id,Model model,RedirectAttributes redirectAttributes){
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }
        try {
            authService.SupprUtilisateur(authService.getUtilisateur(id));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Possède des articles dans la BDD");
            return "redirect:/encheres/admin/ListUtilisateur";
        }


        return "redirect:/encheres/admin/ListUtilisateur";
    }

    @GetMapping("/encheres/admin/ListCategories")
    public String showListCategories(Model model) {
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }

        List<Categorie> categorieList = categorieService.selectAllCategories() ;


        model.addAttribute("categories", categorieList);
        model.addAttribute("cat",new Categorie());
        return "admin/adminCategorie-page";
    }

    @PostMapping("/encheres/admin/modifCat/{id}")
    public String modifAdminCatProcces(@PathVariable(name = "id") Long id,Model model, RedirectAttributes redirectAttributes,
                                       @ModelAttribute("cat") Categorie categorie){
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }
        if (categorie.getLibelle().equals("")){
            redirectAttributes.addFlashAttribute("errorMessage", "non vide");
            return "redirect:/encheres/admin/ListCategories";
        }
        categorie.setNoCategorie(id);
        categorieService.edit(categorie);
        return "redirect:/encheres/admin/ListCategories";
    }


    @GetMapping("/encheres/admin/supprCat/{id}")
    public String supprAdminCatProcces(@PathVariable(name = "id") Long id,Model model, RedirectAttributes redirectAttributes){
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }
        try {
            categorieService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Catégorie non vide");
            return "redirect:/encheres/admin/ListCategories";
        }

        return "redirect:/encheres/admin/ListCategories";
    }

    @PostMapping("/encheres/admin/addCat")
    public String addCategorie(Model model,@RequestParam("newCat") String libelle){
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }
        Categorie c = new Categorie();
        c.setLibelle(libelle);
        categorieService.create(c);

        return "redirect:/encheres/admin/ListCategories";
    }

}
