package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface UtilisateurService {

    public List<Utilisateur> getAllUtilisateur();

    public ServiceResponse<Utilisateur> login(String pseudo, String password);

    public ServiceResponse<Utilisateur> saveUtilisateur(Utilisateur utilisateur) throws SQLException;

    public Utilisateur getUtilisateur(Long id);

    public ServiceResponse<Utilisateur> updateMdp(Utilisateur utilisateur) throws SQLException;

    public Utilisateur getUtilisateurByEmail(String email);

    public void SupprUtilisateur(Utilisateur utilisateur);

}
