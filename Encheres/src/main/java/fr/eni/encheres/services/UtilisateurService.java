package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;

import java.sql.SQLException;

public interface UtilisateurService {


    public ServiceResponse<Utilisateur> login(String pseudo, String password);

    public ServiceResponse<Utilisateur> saveUtilisateur(Utilisateur utilisateur) throws SQLException;

    public Utilisateur getUtilisateur(Long id);

    public ServiceResponse<Utilisateur> updateMdp(Utilisateur utilisateur) throws SQLException;

    public Utilisateur getUtilisateurByEmail(String email);

    public void SupprUtilisateur(Utilisateur utilisateur);

}
