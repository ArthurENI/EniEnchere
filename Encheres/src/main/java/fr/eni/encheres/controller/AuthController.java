package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.ServiceResponse;
import fr.eni.encheres.services.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {
    private final UtilisateurService authService;

    public AuthController(UtilisateurService authService) {
        this.authService = authService;
    }

    @GetMapping("/encheres/")
    public String showLoginForm(Model model) {
        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "auth/login-page";
    }

    @PostMapping("/encheres/login-process")
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
        return "redirect:/encheres/acceuil";
    }

    @GetMapping("encheres/logout")
    public String loginProcess(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        sessionStatus.setComplete();

        EniFlashMessage.sendSuccessFlash(redirectAttributes, "Déconnecter avec succés");

        // Rediriger sur la page d'accueil
        return "redirect:/encheres/acceuil";
    }


    @GetMapping("/encheres/inscription")
    public String showInscriptionForm(Model model) {
        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "auth/inscription-page";
    }

    @GetMapping("/encheres/inscription-process")
    public String InscriptionProcess(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, RedirectAttributes redirectAttributes) {
        ServiceResponse<Utilisateur> serviceResponse = authService.inscriptionUtilisateur(utilisateur);

        return "auth/inscription-page";
    }


    @GetMapping("/encheres/ChgMdp")
    public String showMdpForm(Model model) {
        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "auth/newMdp-page";
    }

}
