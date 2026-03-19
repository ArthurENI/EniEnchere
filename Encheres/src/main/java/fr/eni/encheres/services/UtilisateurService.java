package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurService {


    public ServiceResponse<Utilisateur> login(String pseudo, String password);

    public ServiceResponse<Utilisateur> saveUtilisateur(Utilisateur utilisateur);

    public Utilisateur getUtilisateur(Long id);

    public void SupprUtilisateur(Utilisateur utilisateur);

}
