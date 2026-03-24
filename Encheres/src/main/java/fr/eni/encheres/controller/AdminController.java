package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
public class AdminController {

    private final UtilisateurService authService;

    public AdminController(UtilisateurService authService) {
        this.authService = authService;
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
    public String supprAdminProcces(@PathVariable(name = "id") Long id,Model model){
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("loggedUser");
        if (utilisateur == null || !utilisateur.getRole().getLibelle().equals("admin")){
            return "auth/accesRestreint-page";
        }
        authService.SupprUtilisateur(authService.getUtilisateur(id));

        return "redirect:/encheres/admin/ListUtilisateur";
    }


}
