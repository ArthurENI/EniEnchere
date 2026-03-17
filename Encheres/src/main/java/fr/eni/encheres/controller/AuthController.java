package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.controller.locale.LocaleHelper;
import fr.eni.encheres.services.ServiceResponse;
import fr.eni.encheres.services.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final UtilisateurService authService;
    private final LocaleHelper lh;

    public AuthController(UtilisateurService authService, LocaleHelper lh) {
        this.authService = authService;
        this.lh = lh;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "auth/login-page";
    }

    @PostMapping("/login-process")
    public String loginProcess(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, RedirectAttributes redirectAttributes) {
        // Appel le service
        ServiceResponse<Utilisateur> serviceResponse = authService.login(utilisateur.getPseudo(), utilisateur.getMotDePasse());

        // Si tentative erreur
        if (!serviceResponse.code.equals("2002")) {
            // Envoyer un message d'erreur temporaire
            EniFlashMessage.sendError(model, serviceResponse.message);

            // reafficher le  formulaire
            return "auth/login-page";
        }

        // récupérer le user connecté(e)
        Utilisateur loggedUser = serviceResponse.data;

        // Ajouter dans une session un user
        model.addAttribute("loggedUser", loggedUser);

        EniFlashMessage.sendSuccessFlash(redirectAttributes, serviceResponse.message);

        // Rediriger sur la page d'accueil
        return "redirect:/";
    }
}
