package fr.eni.encheres.controller;

import fr.eni.encheres.bo.Role;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.ServiceResponse;
import fr.eni.encheres.services.UtilisateurService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {
    private final UtilisateurService authService;

    public AuthController(UtilisateurService authService) {
        this.authService = authService;
    }

    private Utilisateur getLoggedUser(Model model) {
        return (Utilisateur) model.getAttribute("loggedUser");
    }
    @GetMapping("/encheres")
    public String showLoginForm(@CookieValue(value = "rememberLogin", required = false) String login, Model model) {
        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(login);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("emailForm", new Utilisateur());

        return "auth/login-page";
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/encheres/login-process")
    public String loginProcess(
            @Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, HttpServletResponse response, @RequestParam(value = "cookiePseudo", required = false) String cookiePseudo) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("emailForm", new Utilisateur());
            return "auth/login-page";
        }

        ServiceResponse<Utilisateur> serviceResponse =
                authService.login(utilisateur.getPseudo(),
                        String.valueOf(utilisateur.getMotDePasse().hashCode()));

        if (!serviceResponse.code.equals("2002")) {
            EniFlashMessage.sendError(model, serviceResponse.message);
            model.addAttribute("emailForm", new Utilisateur());
            return "auth/login-page";
        }
        Utilisateur loggedUser = serviceResponse.data;
        //  SESSION
        model.addAttribute("loggedUser", loggedUser);
        // Cookie
        if (cookiePseudo != null) {
            Cookie cookie = new Cookie("rememberLogin", utilisateur.getPseudo());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        EniFlashMessage.sendSuccessFlash(redirectAttributes, serviceResponse.message);
        return "redirect:/articles/encheres";
    }

    @GetMapping("encheres/logout")
    public String loginProcess(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        sessionStatus.setComplete();

        EniFlashMessage.sendSuccessFlash(redirectAttributes, "Déconnecté avec succés");

        // Rediriger sur la page d'accueil
        return "redirect:/articles/encheres";
    }


    @GetMapping({"/encheres/inscription", "/encheres/ProfilModif/{id}"})
    public String showInscriptionForm(
            @PathVariable(name = "id", required = false) Long id, Model model) {

        Utilisateur utilisateur;

        // Cas modification de profil
        if (id != null) {

           // Utilisateur loggedUser = (Utilisateur) model.getAttribute("loggedUser");
            Utilisateur loggedUser = getLoggedUser(model);
            // Vérification utilisateur connecté
            if (loggedUser == null) {
                return "auth/accesRestreint-page";
            }
            // Vérification que l'utilisateur modifie bien son propre profil
            if (!id.equals(loggedUser.getNoUtilisateur())) {
                return "auth/accesRestreint-page";
            }
            // Récupération utilisateur existant
            utilisateur = authService.getUtilisateur(id);
        } else {
            // Cas inscription
            utilisateur = new Utilisateur();
        }
        model.addAttribute("utilisateur", utilisateur);
        return "auth/inscription-page";
    }


    @PostMapping("/encheres/inscription-process")
    public String inscriptionProcess(
            @Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, @RequestParam("confirmMdp") String confirmMdp) {

        if (bindingResult.hasErrors()) {
            return "auth/inscription-page";
        }

        if (!Objects.equals(utilisateur.getMotDePasse(), confirmMdp)) {
            EniFlashMessage.sendError(model, "Les mots de passe ne correspondent pas");
            return "auth/inscription-page";
        }

        ServiceResponse<Utilisateur> serviceResponse;
        try {
            serviceResponse = authService.saveUtilisateur(utilisateur);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email ou pseudo déjà utilisé");
            return "redirect:/encheres/inscription";
        }
        EniFlashMessage.sendSuccessFlash(redirectAttributes, serviceResponse.message);

        if (serviceResponse.message.equals("Utilisateur créer avec succès")) {
            // Rediriger sur la page de connexion
            return "redirect:/encheres";
        }

        // Ajouter dans une session un user
        model.addAttribute("loggedUser", authService.getUtilisateur(utilisateur.getNoUtilisateur()));
        //page acceuil
//        return "encheres/ListVentes-page";
        return "redirect:/encheres/Profil/"+utilisateur.getNoUtilisateur();
    }

    @GetMapping("/encheres/Profil/{id}")
    public String showProfil(@PathVariable Long id, Model model) {

        Utilisateur loggedUser = getLoggedUser(model);

        if (loggedUser == null) {
            return "auth/accesRestreint-page";
        }

        if (!loggedUser.getNoUtilisateur().equals(id)) {
            return "auth/accesRestreint-page";
        }

        model.addAttribute("utilisateur", loggedUser);
        return "auth/profil-page";
    }

    @GetMapping("/encheres/suppr")
    public String supprimerCompte(Model model, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        Utilisateur utilisateur = getLoggedUser(model);
        sessionStatus.setComplete();
        authService.SupprUtilisateur(utilisateur);

        EniFlashMessage.sendSuccessFlash(redirectAttributes, "Compte supprimé avec succés");

        // Rediriger sur la page d'accueil
        return "redirect:/articles/encheres";

    }


    @PostMapping("/encheres/ChgProcess")
    public String EmailProcess(@ModelAttribute("emailForm") Utilisateur emailForm, Model model, RedirectAttributes redirectAttributes) {

        Utilisateur utilisateur = null;
        utilisateur = authService.getUtilisateurByEmail(emailForm.getEmail());
        if (utilisateur == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "email incorrect");
            return "redirect:/encheres";
        }

        model.addAttribute("utilisateur", utilisateur);

        return "redirect:/encheres/ChgMdp/" + utilisateur.getNoUtilisateur();
    }

    @GetMapping("/encheres/ChgMdp/{id}")
    public String showMdpForm(@PathVariable(name = "id") Long id, Model model) {


        // Instancier un user par defaut dans le formulaire
        Utilisateur utilisateur = authService.getUtilisateur(id);

        model.addAttribute("utilisateur", utilisateur);

        return "auth/newMdp-page";
    }


    @PostMapping("/encheres/Mdp-process")
    public String mdpProcess(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes, @RequestParam("confirmMdp") String confirmMdp, ServletRequest servletRequest) {

        // Vérification confirmation
        if (!Objects.equals(utilisateur.getMotDePasse(), confirmMdp)) {
            bindingResult.rejectValue(
                    "motDePasse",
                    "error.motDePasse",
                    "Les mots de passe ne correspondent pas"
            );
        }

        // Gestion des erreurs
        if (bindingResult.hasErrors()) {
            return "/auth/newMdp-page";
        }

        ServiceResponse<Utilisateur> serviceResponse = null;
        try {
            serviceResponse = authService.updateMdp(utilisateur);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "erreur SQL");
            return "redirect:/encheres/ChgMdp/" + utilisateur.getNoUtilisateur();
        }

        EniFlashMessage.sendSuccessFlash(redirectAttributes, serviceResponse.message);

        //page acceuil
        return "redirect:/encheres";

    }

    // renvoie à l'acceuil lorsque session est terminée
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/articles/encheres";
        }
        return "/encheres/ListVentes-page";
    }


}
