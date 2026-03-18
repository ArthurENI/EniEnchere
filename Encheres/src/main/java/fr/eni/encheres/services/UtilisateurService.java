package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurService {


    public ServiceResponse<Utilisateur> login(String pseudo, String password);

    public ServiceResponse<Utilisateur> inscriptionUtilisateur(Utilisateur utilisateur);
}
